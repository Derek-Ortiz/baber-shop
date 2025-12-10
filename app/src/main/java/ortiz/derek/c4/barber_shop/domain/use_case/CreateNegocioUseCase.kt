package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.CreateNegocioRequest
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class CreateNegocioUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(createNegocioRequest: CreateNegocioRequest) = repository.createNegocio(createNegocioRequest)
}
