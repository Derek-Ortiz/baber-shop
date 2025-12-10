package ortiz.derek.c4.barber_shop.data.repository

import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioResponse
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioResponse
import ortiz.derek.c4.barber_shop.domain.repository.ServicioRepository
import javax.inject.Inject

class ServicioRepositoryImpl @Inject constructor(private val apiService: ApiService) : ServicioRepository {
    override suspend fun createServicio(createServicioRequest: CreateServicioRequest): CreateServicioResponse {
        return apiService.createServicio(createServicioRequest)
    }

    override suspend fun updateServicio(id: Int, updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse {
        return apiService.updateServicio(id, updateServicioRequest)
    }
}
