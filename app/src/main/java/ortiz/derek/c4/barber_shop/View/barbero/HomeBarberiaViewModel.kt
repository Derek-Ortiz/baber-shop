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
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

@HiltViewModel
class HomeBarberiaViewModel @Inject constructor(
    private val adminRepository: AdminRepository,
    private val barberShopRepository: BarberShopRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HomeBarberiaState>(HomeBarberiaState.Loading)
    val state: State<HomeBarberiaState> = _state

    private var currentNegocioId: Int? = null

    init {
        checkUserNegocio()
    }

    private fun checkUserNegocio() {
        viewModelScope.launch {
            _state.value = HomeBarberiaState.Loading
            try {
                val negocioId = userPreferencesRepository.userData.first().negocioId
                if (negocioId != null && negocioId != 0) {
                    currentNegocioId = negocioId
                    fetchNegocioDetails(negocioId)
                } else {
                    _state.value = HomeBarberiaState.NoNegocio
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error al verificar el negocio.")
            }
        }
    }

    private fun fetchNegocioDetails(negocioId: Int) {
        viewModelScope.launch {
            _state.value = HomeBarberiaState.Loading
            try {
                val response = barberShopRepository.getNegocio(negocioId)
                if (response.success) {
                    _state.value = HomeBarberiaState.NegocioLoaded(response.data)
                } else {
                    _state.value = HomeBarberiaState.Error(response.message ?: "No se pudieron cargar los detalles del negocio.")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error de red al cargar el negocio.")
            }
        }
    }

    fun createNegocio(nombre: String, direccion: String) {
        viewModelScope.launch {
            _state.value = HomeBarberiaState.Loading
            try {
                val response = adminRepository.createNegocio(nombre, direccion)
                if (response.success) {
                    _state.value = HomeBarberiaState.NegocioCreated
                } else {
                    _state.value = HomeBarberiaState.Error(response.message ?: "Error al crear el negocio.")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error desconocido al crear el negocio.")
            }
        }
    }

    fun createHorario(dia: String, horaApertura: String, horaCierre: String) {
        viewModelScope.launch {
            val negocioId = currentNegocioId
            if (negocioId == null) {
                _state.value = HomeBarberiaState.Error("No se puede añadir horario sin un negocio.")
                return@launch
            }
            try {
                val response = adminRepository.createHorario(dia, horaApertura, horaCierre, negocioId)
                if (response.success) {
                    // Si se crea el horario, recargamos los detalles del negocio para ver el cambio
                    fetchNegocioDetails(negocioId)
                } else {
                    // Idealmente, aquí se mostraría un error temporal sin cambiar toda la vista
                }
            } catch (e: Exception) {
                // Manejar error de red
            }
        }
    }
}

sealed class HomeBarberiaState {
    object Loading : HomeBarberiaState()
    object NoNegocio : HomeBarberiaState() // El usuario es barbero pero no tiene negocio
    object NegocioCreated : HomeBarberiaState() // El negocio se creó, podría navegar o mostrar un mensaje
    data class NegocioLoaded(val negocioData: GetNegocioResponseData) : HomeBarberiaState()
    data class Error(val message: String) : HomeBarberiaState()
}
