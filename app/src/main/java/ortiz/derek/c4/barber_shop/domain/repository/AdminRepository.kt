package ortiz.derek.c4.barber_shop.domain.repository

import ortiz.derek.c4.barber_shop.data.remote.dto.*

interface AdminRepository {

    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun register(registerRequest: RegisterRequest): RegisterResponse

    suspend fun createNegocio(createNegocioRequest: CreateNegocioRequest): CreateNegocioResponse

    suspend fun getNegocio(id: Int): GetNegocioResponse

    suspend fun createHorario(createHorarioRequest: CreateHorarioRequest): CreateHorarioResponse

    suspend fun createServicio(createServicioRequest: CreateServicioRequest): CreateServicioResponse

    suspend fun updateServicio(id: Int, updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse

    suspend fun deleteServicio(id: Int): DeleteServicioResponse
}
