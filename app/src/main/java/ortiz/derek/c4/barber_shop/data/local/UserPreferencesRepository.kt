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
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val USER_ID = intPreferencesKey("user_id")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PASSWORD = stringPreferencesKey("user_password")
        val NEGOCIO_ID = intPreferencesKey("negocio_id")
        val ADMIN_PHONE = stringPreferencesKey("admin_phone")
    }

    val userData: Flow<UserData> = context.dataStore.data
        .map {
            UserData(
                userId = it[PreferencesKeys.USER_ID],
                email = it[PreferencesKeys.USER_EMAIL],
                password = it[PreferencesKeys.USER_PASSWORD],
                negocioId = it[PreferencesKeys.NEGOCIO_ID],
                adminPhone = it[PreferencesKeys.ADMIN_PHONE]
            )
        }

    suspend fun saveUserData(userId: Int, email: String, password: String, negocioId: Int?, adminPhone: String?) {
        context.dataStore.edit {
            it[PreferencesKeys.USER_ID] = userId
            it[PreferencesKeys.USER_EMAIL] = email
            it[PreferencesKeys.USER_PASSWORD] = password
            if (negocioId != null) {
                it[PreferencesKeys.NEGOCIO_ID] = negocioId
            } else {
                it.remove(PreferencesKeys.NEGOCIO_ID)
            }
            if (adminPhone != null){
                it[PreferencesKeys.ADMIN_PHONE] = adminPhone
            } else {
                it.remove(PreferencesKeys.ADMIN_PHONE)
            }
        }
    }

    suspend fun saveNegocioId(negocioId: Int) {
        context.dataStore.edit {
            it[PreferencesKeys.NEGOCIO_ID] = negocioId
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
    val password: String?,
    val negocioId: Int?,
    val adminPhone: String?
)
