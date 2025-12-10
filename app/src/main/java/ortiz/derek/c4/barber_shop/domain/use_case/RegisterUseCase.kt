package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.RegisterRequest
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(registerRequest: RegisterRequest) = repository.register(registerRequest)
}
