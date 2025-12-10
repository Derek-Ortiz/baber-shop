package ortiz.derek.c4.barber_shop.domain.repository

import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.CreateServicioResponse
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.UpdateServicioResponse

interface ServicioRepository {
    suspend fun createServicio(createServicioRequest: CreateServicioRequest): CreateServicioResponse
    suspend fun updateServicio(id: Int, updateServicioRequest: UpdateServicioRequest): UpdateServicioResponse
}
