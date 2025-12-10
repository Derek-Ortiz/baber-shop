package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class DeleteServicioUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteServicio(id)
}
