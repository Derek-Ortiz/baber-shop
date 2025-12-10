package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioRequest
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class CreateServicioUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(createServicioRequest: CreateServicioRequest) = repository.createServicio(createServicioRequest)
}
