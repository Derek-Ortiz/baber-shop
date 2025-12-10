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
import ortiz.derek.c4.barber_shop.domain.use_case.GetNegocioUseCase
import javax.inject.Inject

@HiltViewModel
class HomeBarberoViewModel @Inject constructor(
    private val getNegocioUseCase: GetNegocioUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HomeBarberoState>(HomeBarberoState.Loading)
    val state: State<HomeBarberoState> = _state

    init {
        viewModelScope.launch {
            val userData = userPreferencesRepository.userData.first()
            val negocioId = userData.negocioId
            if (negocioId != null) {
                getNegocio(negocioId)
            } else {
                _state.value = HomeBarberoState.Error("No se encontró el ID del negocio.")
            }
        }
    }

    private fun getNegocio(id: Int) {
        viewModelScope.launch {
            _state.value = HomeBarberoState.Loading
            try {
                val response = getNegocioUseCase(id)
                if (response.success) {
                    _state.value = HomeBarberoState.Success(response.data)
                } else {
                    _state.value = HomeBarberoState.Error(response.message ?: "Error al obtener el negocio.")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberoState.Error(e.message ?: "Error desconocido.")
            }
        }
    }
}

sealed class HomeBarberoState {
    object Loading : HomeBarberoState()
    data class Success(val negocioData: GetNegocioResponseData) : HomeBarberoState()
    data class Error(val message: String) : HomeBarberoState()
}
