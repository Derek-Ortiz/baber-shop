package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.helpers.Result
import javax.inject.Inject

@HiltViewModel
class FechaHoraViewModel @Inject constructor(
    private val repository: BarberShopRepository
) : ViewModel() {

    private val _reservaState = MutableStateFlow<Result<Cita>>(Result.Idle)
    val reservaState: StateFlow<Result<Cita>> = _reservaState.asStateFlow()

    fun resetState() {
        _reservaState.value = Result.Idle
    }

    fun crearCita(request: CitaRequest) {
        viewModelScope.launch {
            _reservaState.value = Result.Loading
            try {
                val result = repository.crearCita(request)
                _reservaState.value = Result.Success(result)
            } catch (e: Exception) {
                _reservaState.value = Result.Error(e.message ?: "Error al reservar")
            }
        }
    }
}
