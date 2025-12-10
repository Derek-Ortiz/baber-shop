package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.Models.Servicio
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioRequest
import ortiz.derek.c4.barber_shop.domain.use_case.CreateServicioUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.DeleteServicioUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.GetNegocioUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.UpdateServicioUseCase
import javax.inject.Inject

@HiltViewModel
class ServicioViewModel @Inject constructor(
    private val createServicioUseCase: CreateServicioUseCase,
    private val updateServicioUseCase: UpdateServicioUseCase,
    private val deleteServicioUseCase: DeleteServicioUseCase,
    private val getNegocioUseCase: GetNegocioUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val services = mutableStateOf<List<Servicio>>(emptyList())

    init {
        loadServices()
    }

    private fun loadServices() {
        viewModelScope.launch {
            val negocioId = userPreferencesRepository.userData.first().negocioId
            if (negocioId != null) {
                val negocioResponse = getNegocioUseCase(negocioId)
                if (negocioResponse.success) {
                    services.value = negocioResponse.data.servicios.map { it.toServicio() }
                }
            }
        }
    }

    fun createServicio(nombre: String, precio: Double, duracion: Int) {
        viewModelScope.launch {
            val negocioId = userPreferencesRepository.userData.first().negocioId
            if (negocioId != null) {
                val request = CreateServicioRequest(nombre, precio, duracion, negocioId)
                val response = createServicioUseCase(request)
                if (response.success) {
                    loadServices()
                }
            }
        }
    }

    fun updateServicio(servicio: Servicio) {
        viewModelScope.launch {
            val negocioId = userPreferencesRepository.userData.first().negocioId
            if (negocioId != null) {
                val request = UpdateServicioRequest(servicio.nombre, servicio.precio, servicio.duracion, negocioId)
                val response = updateServicioUseCase(servicio.id, request)
                if (response.success) {
                    loadServices()
                }
            }
        }
    }

    fun deleteServicio(servicio: Servicio) {
        viewModelScope.launch {
            val response = deleteServicioUseCase(servicio.id)
            if (response.success) {
                loadServices()
            }
        }
    }
}
