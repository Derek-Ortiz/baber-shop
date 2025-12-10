package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.model.Negocio
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.helpers.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BarberShopRepository
) : ViewModel() {

    private val _negocios = MutableStateFlow<Result<List<Negocio>>>(Result.Idle)
    val negocios: StateFlow<Result<List<Negocio>>> = _negocios.asStateFlow()

    init {
        fetchNegocios()
    }

    fun fetchNegocios() {
        viewModelScope.launch {
            _negocios.value = Result.Loading
            try {
                val result = repository.getNegocios()
                _negocios.value = Result.Success(result)
            } catch (e: Exception) {
                _negocios.value = Result.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
