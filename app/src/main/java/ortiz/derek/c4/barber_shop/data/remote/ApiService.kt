package ortiz.derek.c4.barber_shop.data.remote

import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import retrofit2.http.*

interface ApiService {

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

    @POST("servicios")
    suspend fun createServicio(@Body createServicioRequest: CreateServicioRequest): CreateServicioResponse

    @PUT("servicios/{id}")
    suspend fun updateServicio(@Path("id") id: Int, @Body updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse

    @DELETE("servicios/{id}")
    suspend fun deleteServicio(@Path("id") id: Int): DeleteServicioResponse

    @GET("negocios")
    suspend fun getNegocios(): GetNegociosResponse

    @POST("auth/clientes/register")
    suspend fun registrarCliente(@Body request: ClienteRequest): RegisterClienteResponse

    @POST("citas")
    suspend fun crearCita(@Body request: CitaRequest): CreateCitaResponse

    @GET("citas/cliente/{id}")
    suspend fun getCitasCliente(@Path("id") id: Int): GetCitasClienteResponse
}
