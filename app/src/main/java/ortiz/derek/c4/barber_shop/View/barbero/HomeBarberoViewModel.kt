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
import ortiz.derek.c4.barber_shop.data.remote.dto.GetNegocioResponseData
import ortiz.derek.c4.barber_shop.domain.use_case.GetCitasUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.GetNegocioUseCase
import javax.inject.Inject

@HiltViewModel
class HomeBarberoViewModel @Inject constructor(
    private val getNegocioUseCase: GetNegocioUseCase,
    private val getCitasUseCase: GetCitasUseCase, // Add this
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _state = mutableStateOf<HomeBarberoState>(HomeBarberoState.Loading)
    val state: State<HomeBarberoState> = _state

    init {
        viewModelScope.launch {
            try {
                val userData = userPreferencesRepository.userData.first()
                val negocioId = userData.negocioId
                val adminPhone = userData.adminPhone
                if (negocioId != null) {
                    getNegocioAndCitas(negocioId, adminPhone)
                } else {
                    _state.value = HomeBarberoState.Error("No se encontró el ID del negocio.")
                }
            } catch (e: Exception) {
                _state.value = HomeBarberoState.Error(e.message ?: "Error al cargar los datos de usuario.")
            }
        }
    }

    private fun getNegocioAndCitas(id: Int, adminPhone: String?) {
        viewModelScope.launch {
            _state.value = HomeBarberoState.Loading
            try {
                val negocioResponse = getNegocioUseCase(id)
                val citasResponse = getCitasUseCase(id)

                if (negocioResponse.success && citasResponse.success) {
                    _state.value = HomeBarberoState.Success(
                        negocioData = negocioResponse.data,
                        adminPhone = adminPhone,
                        citas = citasResponse.data
                    )
                } else {
                    val errorMessage = if (!negocioResponse.success) {
                        negocioResponse.message ?: "Error al obtener el negocio."
                    } else {
                        citasResponse.message ?: "Error al obtener las citas."
                    }
                    _state.value = HomeBarberoState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _state.value = HomeBarberoState.Error(e.message ?: "Error desconocido.")
            }
        }
    }
}

sealed class HomeBarberoState {
    object Loading : HomeBarberoState()
    data class Success(
        val negocioData: GetNegocioResponseData,
        val adminPhone: String?,
        val citas: List<CitaData>
    ) : HomeBarberoState()

    data class Error(val message: String) : HomeBarberoState()
}
