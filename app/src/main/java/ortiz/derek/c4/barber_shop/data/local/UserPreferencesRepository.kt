package ortiz.derek.c4.barber_shop.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ortiz.derek.c4.barber_shop.data.remote.dto.ClienteDto
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val USER_ID = intPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val NOMBRES = stringPreferencesKey("user_nombres")
        val APELLIDO_P = stringPreferencesKey("user_apellido_p")
        val APELLIDO_M = stringPreferencesKey("user_apellido_m")
        val TELEFONO = stringPreferencesKey("user_telefono")
        val DIRECCION = stringPreferencesKey("user_direccion")
        val NEGOCIO_ID = intPreferencesKey("negocio_id")
        val USER_TYPE = stringPreferencesKey("user_type")
    }

    val userData: Flow<UserData> = context.dataStore.data
        .map { preferences ->
            UserData(
                userId = preferences[PreferencesKeys.USER_ID],
                email = preferences[PreferencesKeys.USER_EMAIL],
                nombres = preferences[PreferencesKeys.NOMBRES],
                apellidoP = preferences[PreferencesKeys.APELLIDO_P],
                apellidoM = preferences[PreferencesKeys.APELLIDO_M],
                telefono = preferences[PreferencesKeys.TELEFONO],
                direccion = preferences[PreferencesKeys.DIRECCION],
                negocioId = preferences[PreferencesKeys.NEGOCIO_ID],
                userType = preferences[PreferencesKeys.USER_TYPE]
            )
        }

    suspend fun saveClientData(cliente: ClienteDto) {
        context.dataStore.edit {
            it[PreferencesKeys.USER_ID] = cliente.id
            it[PreferencesKeys.USER_EMAIL] = cliente.email
            it[PreferencesKeys.NOMBRES] = cliente.nombres
            it[PreferencesKeys.APELLIDO_P] = cliente.apellidoP
            it[PreferencesKeys.APELLIDO_M] = cliente.apellidoM
            it[PreferencesKeys.TELEFONO] = cliente.telefono
            it[PreferencesKeys.DIRECCION] = cliente.direccion
            it[PreferencesKeys.USER_TYPE] = "cliente"
            it.remove(PreferencesKeys.NEGOCIO_ID) // Los clientes no tienen negocioId
        }
    }

    suspend fun saveAdminData(admin: LoginResponse.Administrador) {
        context.dataStore.edit {
            it[PreferencesKeys.USER_ID] = admin.id
            it[PreferencesKeys.USER_EMAIL] = admin.email
            it[PreferencesKeys.NOMBRES] = admin.nombres
            it[PreferencesKeys.APELLIDO_P] = admin.apellidoP
            it[PreferencesKeys.APELLIDO_M] = admin.apellidoM
            it[PreferencesKeys.TELEFONO] = admin.telefono
            it[PreferencesKeys.USER_TYPE] = "barbero"
            admin.negocioId?.let { negocioId -> it[PreferencesKeys.NEGOCIO_ID] = negocioId }
        }
    }

    suspend fun clearData(){
        context.dataStore.edit {
            it.clear()
        }
    }
}

data class UserData(
    val userId: Int?,
    val email: String?,
    val nombres: String?,
    val apellidoP: String?,
    val apellidoM: String?,
    val telefono: String?,
    val direccion: String?,
    val negocioId: Int?,
    val userType: String?
)
