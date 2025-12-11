package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.GetNegocioResponseData
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

@HiltViewModel
class HomeBarberoViewModel @Inject constructor(
    private val repository: BarberShopRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HomeBarberoState>(HomeBarberoState.Loading)
    val state: State<HomeBarberoState> = _state

    init {
        // Cargar los datos del negocio cuando el ViewModel se inicializa
        loadNegocioData()
    }

    private fun loadNegocioData() {
        viewModelScope.launch {
            _state.value = HomeBarberoState.Loading
            try {
                // 1. Obtener el ID del negocio desde las preferencias del usuario
                val negocioId = userPreferencesRepository.userData.first().negocioId

                if (negocioId != null && negocioId != 0) {
                    // 2. Si hay un ID, obtener los detalles del negocio
                    val response = repository.getNegocio(negocioId)
                    if (response.success) {
                        _state.value = HomeBarberoState.Success(response.data)
                    } else {
                        _state.value = HomeBarberoState.Error(response.message ?: "Error al obtener los datos del negocio.")
                    }
                } else {
                    // 3. Si no hay ID, el usuario es un barbero sin negocio asignado
                    _state.value = HomeBarberoState.Error("Este usuario no tiene un negocio asignado.")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberoState.Error(e.message ?: "Ocurrió un error desconocido.")
            }
        }
    }
    
    // Función para reintentar la carga en caso de error
    fun retry() {
        loadNegocioData()
    }
}

// Estados de la UI para esta pantalla
sealed class HomeBarberoState {
    object Loading : HomeBarberoState() // Muestra un indicador de carga
    data class Success(val negocioData: GetNegocioResponseData) : HomeBarberoState() // Muestra los datos del negocio
    data class Error(val message: String) : HomeBarberoState() // Muestra un mensaje de error
}
