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

    override suspend fun register(registerRequest: AdminRegisterRequest): RegisterResponse {
        return withContext(Dispatchers.IO) {
            apiService.register(registerRequest)
        }
    }

    override suspend fun createNegocio(nombre: String, direccion: String): CreateNegocioResponse {
        val request = CreateNegocioRequest(nombre, direccion)
        return withContext(Dispatchers.IO) {
            apiService.createNegocio(request)
        }
    }

    override suspend fun getNegocio(id: Int): GetNegocioResponse {
        return withContext(Dispatchers.IO) {
            apiService.getNegocio(id)
        }
    }

    override suspend fun createHorario(dia: String, horaApertura: String, horaCierre: String, negocioId: Int): CreateHorarioResponse {
        val request = CreateHorarioRequest(dia, horaApertura, horaCierre, negocioId)
        return withContext(Dispatchers.IO) {
            apiService.createHorario(request)
        }
    }
}
