package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioResponse
import ortiz.derek.c4.barber_shop.domain.repository.ServicioRepository
import javax.inject.Inject

class CreateServicioUseCase @Inject constructor(private val repository: ServicioRepository) {
    suspend operator fun invoke(createServicioRequest: CreateServicioRequest): CreateServicioResponse {
        return repository.createServicio(createServicioRequest)
    }
}
