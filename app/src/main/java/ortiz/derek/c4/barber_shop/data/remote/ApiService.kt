package ortiz.derek.c4.barber_shop.data.remote

import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("citas/negocio/{id}")
    suspend fun getCitas(@Path("id") id: Int): CitasResponse

    @POST("api/servicios")
    suspend fun createServicio(@Body createServicioRequest: CreateServicioRequest): CreateServicioResponse

    @PUT("api/servicios/{id}")
    suspend fun updateServicio(@Path("id") id: Int, @Body updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse


    // --- Client Endpoints ---
    @GET("negocios")
    suspend fun getNegocios(): ApiResponse<List<NegocioDto>>

    @GET("negocios/{id}")
    suspend fun getNegocioDetalle(@Path("id") id: Int): ApiResponse<NegocioDto>

    @POST("auth/cliente/register")
    suspend fun registrarCliente(@Body request: ClienteRequest): ApiResponse<ClienteDto>

    @POST("citas")
    suspend fun crearCita(@Body request: CitaRequest): ApiResponse<CitaDto>

    @GET("citas/cliente/{clienteId}")
    suspend fun getCitasCliente(@Path("clienteId") clienteId: Int): ApiResponse<List<CitaDto>>
}
