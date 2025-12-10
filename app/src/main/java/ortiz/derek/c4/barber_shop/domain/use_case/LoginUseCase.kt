package ortiz.derek.c4.barber_shop.domain.use_case

import ortiz.derek.c4.barber_shop.data.remote.dto.LoginRequest
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AdminRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest) = repository.login(loginRequest)
}
