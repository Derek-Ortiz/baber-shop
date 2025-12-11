package ortiz.derek.c4.barber_shop.data.remote

import ortiz.derek.c4.barber_shop.data.model.ApiResponse
import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // --- Admin Endpoints ---
    @POST("auth/admin/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/admin/register")
    suspend fun register(@Body registerRequest: AdminRegisterRequest): RegisterResponse

    @POST("negocios")
    suspend fun createNegocio(@Body createNegocioRequest: CreateNegocioRequest): CreateNegocioResponse

    @GET("negocios/{id}")
    suspend fun getNegocio(@Path("id") id: Int): GetNegocioResponse

    @POST("horarios")
    suspend fun createHorario(@Body createHorarioRequest: CreateHorarioRequest): CreateHorarioResponse

    @POST("servicios")
    suspend fun addServicio(@Body servicio: ServicioDto): AddServicioResponse

    @PUT("servicios/{id}")
    suspend fun updateServicio(@Path("id") id: Int, @Body servicio: ServicioDto): GenericResponse

    @DELETE("servicios/{id}")
    suspend fun deleteServicio(@Path("id") id: Int): GenericResponse

    @GET("citas/negocio/{negocioId}")
    suspend fun getHistorialCitas(@Path("negocioId") negocioId: Int): HistorialCitasResponse

    // --- Client Endpoints ---
    @GET("negocios")
    suspend fun getNegocios(): ApiResponse<List<NegocioDto>>

    @GET("servicios/negocio/{negocioId}")
    suspend fun getServicios(@Path("negocioId") negocioId: Int): GetServiciosResponse

    @POST("auth/cliente/register")
    suspend fun registrarCliente(@Body request: ClienteRequest): GenericResponse

    @POST("citas")
    suspend fun crearCita(@Body request: CitaRequest): AddCitaResponse

    @GET("citas/cliente/{clienteId}")
    suspend fun getCitasCliente(@Path("clienteId") clienteId: Int): ApiResponse<List<Cita>>
}
