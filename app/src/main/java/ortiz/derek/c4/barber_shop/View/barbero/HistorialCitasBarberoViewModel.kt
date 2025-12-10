package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.CitaData
import ortiz.derek.c4.barber_shop.domain.use_case.GetCitasUseCase
import javax.inject.Inject

@HiltViewModel
class HistorialCitasBarberoViewModel @Inject constructor(
    private val getCitasUseCase: GetCitasUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HistorialCitasState>(HistorialCitasState.Loading)
    val state: State<HistorialCitasState> = _state

    init {
        getCitas()
    }

    private fun getCitas() {
        viewModelScope.launch {
            _state.value = HistorialCitasState.Loading
            try {
                val userData = userPreferencesRepository.userData.first()
                val negocioId = userData.negocioId
                if (negocioId != null && negocioId != 0 && negocioId != -1) {
                    val response = getCitasUseCase(negocioId)
                    if (response.success) {
                        _state.value = HistorialCitasState.Success(response.data)
                    } else {
                        _state.value = HistorialCitasState.Error(response.message ?: "Error al obtener las citas.")
                    }
                } else {
                    _state.value = HistorialCitasState.Error("ID de negocio no válido.")
                }
            } catch (e: Exception) {
                _state.value = HistorialCitasState.Error(e.message ?: "Error desconocido.")
            }
        }
    }
}

sealed class HistorialCitasState {
    object Loading : HistorialCitasState()
    data class Success(val citas: List<CitaData>) : HistorialCitasState()
    data class Error(val message: String) : HistorialCitasState()
}
