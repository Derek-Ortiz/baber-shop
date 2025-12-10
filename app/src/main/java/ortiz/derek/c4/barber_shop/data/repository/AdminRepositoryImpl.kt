package ortiz.derek.c4.barber_shop.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AdminRepository {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return withContext(Dispatchers.IO) {
            apiService.login(loginRequest)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        return withContext(Dispatchers.IO) {
            apiService.register(registerRequest)
        }
    }

    override suspend fun createNegocio(createNegocioRequest: CreateNegocioRequest): CreateNegocioResponse {
        return withContext(Dispatchers.IO) {
            apiService.createNegocio(createNegocioRequest)
        }
    }

    override suspend fun getNegocio(id: Int): GetNegocioResponse {
        return withContext(Dispatchers.IO) {
            apiService.getNegocio(id)
        }
    }

    override suspend fun createHorario(createHorarioRequest: CreateHorarioRequest): CreateHorarioResponse {
        return withContext(Dispatchers.IO) {
            apiService.createHorario(createHorarioRequest)
        }
    }
}
