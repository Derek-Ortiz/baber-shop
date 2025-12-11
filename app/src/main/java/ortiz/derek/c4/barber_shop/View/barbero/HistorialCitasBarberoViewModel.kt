package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.CitaCompletaDto
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistorialCitasBarberoViewModel @Inject constructor(
    private val repository: BarberShopRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HistorialCitasState>(HistorialCitasState.Loading)
    val state: State<HistorialCitasState> = _state

    private var negocioId: Int? = null
    private var allCitas = listOf<CitaCompletaDto>()

    init {
        loadHistorialCitas()
    }

    private fun loadHistorialCitas() {
        viewModelScope.launch {
            _state.value = HistorialCitasState.Loading
            try {
                val userData = userPreferencesRepository.userData.first()
                negocioId = userData.negocioId
                if (negocioId != null) {
                    val response = repository.getHistorialCitas(negocioId!!)
                    if (response.success) {
                        allCitas = response.data
                        _state.value = HistorialCitasState.Success(allCitas)
                    } else {
                        _state.value = HistorialCitasState.Error(response.message ?: "Error al cargar el historial.")
                    }
                } else {
                    _state.value = HistorialCitasState.Error("ID de negocio no encontrado.")
                }
            } catch (e: Exception) {
                _state.value = HistorialCitasState.Error(e.message ?: "Error desconocido.")
            }
        }
    }

    fun filterCitasByDate(startDate: Date?, endDate: Date?) {
        if (startDate == null || endDate == null) {
            _state.value = HistorialCitasState.Success(allCitas)
            return
        }

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val filteredList = allCitas.filter {
            try {
                val citaDate = sdf.parse(it.cita.fechaCita)
                !citaDate.before(startDate) && !citaDate.after(endDate)
            } catch (e: Exception) {
                false
            }
        }
        _state.value = HistorialCitasState.Success(filteredList)
    }
}

sealed class HistorialCitasState {
    object Loading : HistorialCitasState()
    data class Success(val citas: List<CitaCompletaDto>) : HistorialCitasState()
    data class Error(val message: String) : HistorialCitasState()
}