package ortiz.derek.c4.barber_shop.data.repository

import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.Cliente
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.model.Negocio
import ortiz.derek.c4.barber_shop.data.model.Servicio
import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

class BarberShopRepositoryImpl @Inject constructor(private val apiService: ApiService) : BarberShopRepository {

    override suspend fun getNegocios(): List<Negocio> {
        val response = apiService.getNegocios()
        if (response.success) {
            return response.data ?: emptyList()
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getServicios(negocioId: Int): List<Servicio> {
        val response = apiService.getNegocioDetalle(negocioId)
        if (response.success) {
            return response.data?.servicios ?: emptyList()
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun registrarCliente(request: ClienteRequest): Cliente {
        val response = apiService.registrarCliente(request)
        if (response.success && response.data != null) {
            return response.data
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun crearCita(request: CitaRequest): Cita {
        val response = apiService.crearCita(request)
        if (response.success && response.data != null) {
            return response.data
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getCitasCliente(clienteId: Int): List<Cita> {
        val response = apiService.getCitasCliente(clienteId)
        if (response.success) {
            return response.data ?: emptyList()
        } else {
            throw Exception(response.message)
        }
    }
}
