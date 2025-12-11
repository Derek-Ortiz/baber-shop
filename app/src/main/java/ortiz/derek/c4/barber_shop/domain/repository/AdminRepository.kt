package ortiz.derek.c4.barber_shop.domain.repository

import ortiz.derek.c4.barber_shop.data.remote.dto.*

interface AdminRepository {

    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun register(registerRequest: AdminRegisterRequest): RegisterResponse

    // Se cambia para aceptar parámetros directos en lugar de un objeto Request
    suspend fun createNegocio(nombre: String, direccion: String): CreateNegocioResponse

    suspend fun getNegocio(id: Int): GetNegocioResponse

    // Se cambia para aceptar parámetros directos en lugar de un objeto Request
    suspend fun createHorario(dia: String, horaApertura: String, horaCierre: String, negocioId: Int): CreateHorarioResponse
}
