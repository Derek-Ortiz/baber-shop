package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateHorarioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateNegocioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.GetNegocioResponseData
import ortiz.derek.c4.barber_shop.domain.use_case.CreateHorarioUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.CreateNegocioUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.GetNegocioUseCase
import javax.inject.Inject

@HiltViewModel
class HomeBarberiaViewModel @Inject constructor(
    private val createNegocioUseCase: CreateNegocioUseCase,
    private val getNegocioUseCase: GetNegocioUseCase,
    private val createHorarioUseCase: CreateHorarioUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HomeBarberiaState>(HomeBarberiaState.NoNegocio)
    val state: State<HomeBarberiaState> = _state

    init {
        viewModelScope.launch {
            val userData = userPreferencesRepository.userData.first()
            userData.negocioId?.let {
                getNegocio(it)
            }
        }
    }

    fun createNegocio(nombre: String, direccion: String) {
        viewModelScope.launch {
            _state.value = HomeBarberiaState.Loading
            try {
                val response = createNegocioUseCase(CreateNegocioRequest(nombre, direccion))
                if (response.success) {
                    userPreferencesRepository.saveNegocioId(response.data.id)
                    _state.value = HomeBarberiaState.NavigateToHome
                } else {
                    _state.value = HomeBarberiaState.Error("Error al crear el negocio")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    private fun getNegocio(id: Int) {
        viewModelScope.launch {
            _state.value = HomeBarberiaState.Loading
            try {
                val response = getNegocioUseCase(id)
                if (response.success) {
                    _state.value = HomeBarberiaState.NegocioLoaded(response.data)
                } else {
                    _state.value = HomeBarberiaState.Error(response.message)
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun createHorario(dia: String, horaApertura: String, horaCierre: String, negocioId: Int) {
        viewModelScope.launch {
            try {
                val response = createHorarioUseCase(CreateHorarioRequest(dia, horaApertura, horaCierre, negocioId))
                if (response.success) {
                    // Refresh negocio data
                    getNegocio(negocioId)
                } else {
                    _state.value = HomeBarberiaState.Error("Error al crear el horario")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberiaState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class HomeBarberiaState {
    object Loading : HomeBarberiaState()
    object NoNegocio : HomeBarberiaState()
    object NavigateToHome : HomeBarberiaState()
    data class NegocioLoaded(val negocioData: GetNegocioResponseData) : HomeBarberiaState()
    data class Error(val message: String) : HomeBarberiaState()
}
