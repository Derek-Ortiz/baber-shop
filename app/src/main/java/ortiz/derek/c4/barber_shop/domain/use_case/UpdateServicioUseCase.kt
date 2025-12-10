package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioResponse
import ortiz.derek.c4.barber_shop.domain.repository.ServicioRepository
import javax.inject.Inject

class UpdateServicioUseCase @Inject constructor(private val repository: ServicioRepository) {
    suspend operator fun invoke(id: Int, updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse {
        return repository.updateServicio(id, updateServicioRequest)
    }
}
