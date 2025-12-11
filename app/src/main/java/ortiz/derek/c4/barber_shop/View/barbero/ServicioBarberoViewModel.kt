package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.ServicioDto
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

@HiltViewModel
class ServicioBarberoViewModel @Inject constructor(
    private val repository: BarberShopRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<ServicioBarberoState>(ServicioBarberoState.Loading)
    val state: State<ServicioBarberoState> = _state

    private var negocioId: Int? = null

    init {
        loadServicios()
    }

    fun loadServicios() {
        viewModelScope.launch {
            _state.value = ServicioBarberoState.Loading
            try {
                val id = userPreferencesRepository.userData.first().negocioId
                if (id != null && id != 0) {
                    negocioId = id
                    val response = repository.getNegocio(id)
                    if (response.success) {
                        _state.value = ServicioBarberoState.Success(response.data.servicios)
                    } else {
                        _state.value = ServicioBarberoState.Error(response.message ?: "Error al cargar los servicios.")
                    }
                } else {
                    _state.value = ServicioBarberoState.Error("ID de negocio no encontrado.")
                }
            } catch (e: Exception) {
                _state.value = ServicioBarberoState.Error(e.message ?: "Error desconocido al cargar servicios.")
            }
        }
    }

    fun addServicio(nombre: String, precio: Double, duracion: Int) {
        viewModelScope.launch {
            val currentId = negocioId
            if (currentId == null) {
                // Opcional: Podrías actualizar el estado a un error específico
                return@launch
            }
            try {
                val response = repository.addServicio(nombre, precio, duracion, currentId)
                if (response.success) {
                    loadServicios() // Recargar la lista para mostrar el nuevo servicio
                } else {
                     // Opcional: Mostrar un error temporal
                }
            } catch (e: Exception) {
                // Opcional: Manejar error de red
            }
        }
    }

    fun updateServicio(servicio: ServicioDto) {
        viewModelScope.launch {
            val currentId = negocioId
             if (currentId == null) {
                return@launch
            }
            try {
                val response = repository.updateServicio(servicio.id, servicio.nombre, servicio.precio.toDouble(), servicio.duracion, currentId)
                if (response.success) {
                    loadServicios() // Recargar la lista
                } else {
                   // Opcional: Mostrar un error temporal
                }
            } catch (e: Exception) {
                 // Opcional: Manejar error de red
            }
        }
    }

    fun deleteServicio(servicioId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.deleteServicio(servicioId)
                if (response.success) {
                    loadServicios() // Recargar la lista
                } else {
                    // Opcional: Mostrar un error temporal
                }
            } catch (e: Exception) {
                // Opcional: Manejar error de red
            }
        }
    }
    
    fun retry(){
        loadServicios()
    }
}

sealed class ServicioBarberoState {
    object Loading : ServicioBarberoState()
    data class Success(val servicios: List<ServicioDto>) : ServicioBarberoState()
    data class Error(val message: String) : ServicioBarberoState()
}
