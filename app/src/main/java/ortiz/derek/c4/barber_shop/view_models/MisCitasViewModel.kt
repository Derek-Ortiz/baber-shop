package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.helpers.Result
import javax.inject.Inject

@HiltViewModel
class MisCitasViewModel @Inject constructor(
    private val repository: BarberShopRepository
) : ViewModel() {

    private val _citas = MutableStateFlow<Result<List<Cita>>>(Result.Idle)
    val citas: StateFlow<Result<List<Cita>>> = _citas.asStateFlow()

    fun fetchCitas(usuarioId: Int) {
        viewModelScope.launch {
            _citas.value = Result.Loading
            try {
                val result = repository.getCitasCliente(usuarioId)
                _citas.value = Result.Success(result)
            } catch (e: Exception) {
                _citas.value = Result.Error(e.message ?: "Error desconocido al obtener las citas")
            }
        }
    }
}
