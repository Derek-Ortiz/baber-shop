package ortiz.derek.c4.barber_shop.data.remote

import ortiz.derek.c4.barber_shop.data.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
}
