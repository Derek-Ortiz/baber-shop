package ortiz.derek.c4.barber_shop.data.remote

import ortiz.derek.c4.barber_shop.data.model.ApiResponse
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.Cliente
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.model.Negocio
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // --- Admin Endpoints ---
    @POST("auth/admin/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/admin/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @POST("negocios")
    suspend fun createNegocio(@Body createNegocioRequest: CreateNegocioRequest): CreateNegocioResponse

    @GET("negocios/{id}")
    suspend fun getNegocio(@Path("id") id: Int): GetNegocioResponse

    @POST("horarios")
    suspend fun createHorario(@Body createHorarioRequest: CreateHorarioRequest): CreateHorarioResponse

    // --- Client Endpoints ---
    @GET("negocios")
    suspend fun getNegocios(): ApiResponse<List<Negocio>>

    @GET("negocios/{id}")
    suspend fun getNegocioDetalle(@Path("id") id: Int): ApiResponse<Negocio>

    @POST("auth/cliente/register")
    suspend fun registrarCliente(@Body request: ClienteRequest): ApiResponse<Cliente>

    @POST("citas")
    suspend fun crearCita(@Body request: CitaRequest): ApiResponse<Cita>

    @GET("citas/cliente/{clienteId}")
    suspend fun getCitasCliente(@Path("clienteId") clienteId: Int): ApiResponse<List<Cita>>
}
