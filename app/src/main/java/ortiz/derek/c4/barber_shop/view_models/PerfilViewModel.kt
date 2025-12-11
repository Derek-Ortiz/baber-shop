package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserData
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // Expone el Flow de UserData y lo convierte en un StateFlow para que la UI lo observe
    val userData = userPreferencesRepository.userData
        .stateIn(
            scope = viewModelScope,
            // Inicia el flujo cuando la UI está visible y lo detiene 5 segundos después de que se va
            started = SharingStarted.WhileSubscribed(5000),
            // Proporciona un valor inicial seguro mientras se cargan los datos reales
            initialValue = UserData(null, null, null, null, null, null, null, null, null)
        )

    // Función para que la UI llame al cerrar sesión
    fun logout() {
        viewModelScope.launch {
            userPreferencesRepository.clearData()
        }
    }
}
