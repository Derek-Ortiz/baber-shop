package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.model.Servicio
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.helpers.Result
import javax.inject.Inject

@HiltViewModel
class ServiciosViewModel @Inject constructor(
    private val repository: BarberShopRepository
) : ViewModel() {

    private val _servicios = MutableStateFlow<Result<List<Servicio>>>(Result.Idle)
    val servicios: StateFlow<Result<List<Servicio>>> = _servicios.asStateFlow()

    fun fetchServicios(barberiaId: Int) {
        viewModelScope.launch {
            _servicios.value = Result.Loading
            try {
                val result = repository.getServicios(barberiaId)
                _servicios.value = Result.Success(result)
            } catch (e: Exception) {
                _servicios.value = Result.Error(e.message ?: "Error desconocido al obtener los servicios")
            }
        }
    }
}
