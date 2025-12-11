package ortiz.derek.c4.barber_shop.data.repository

import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.data.remote.dto.*
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

class BarberShopRepositoryImpl @Inject constructor(private val apiService: ApiService) : BarberShopRepository {

    override suspend fun getNegocios(): List<NegocioDto> {
        val response = apiService.getNegocios()
        if (response.success) {
            return response.data ?: emptyList()
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getServicios(negocioId: Int): GetServiciosResponse {
        return apiService.getServicios(negocioId)
    }

    override suspend fun registrarCliente(request: ClienteRequest): GenericResponse {
        return apiService.registrarCliente(request)
    }

    override suspend fun crearCita(request: CitaRequest): AddCitaResponse {
        return apiService.crearCita(request)
    }

    override suspend fun getCitasCliente(clienteId: Int): List<Cita> {
        val response = apiService.getCitasCliente(clienteId)
        if (response.success) {
            return response.data ?: emptyList()
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getNegocio(id: Int): GetNegocioResponse {
        return apiService.getNegocio(id)
    }

    override suspend fun createNegocio(nombre: String, direccion: String): CreateNegocioResponse {
        val request = CreateNegocioRequest(nombre, direccion)
        return apiService.createNegocio(request)
    }

    override suspend fun addServicio(nombre: String, precio: Double, duracion: Int, negocioId: Int): AddServicioResponse {
        val request = ServicioDto(0, nombre, precio.toFloat(), duracion, negocioId)
        return apiService.addServicio(request)
    }

    override suspend fun updateServicio(id: Int, nombre: String, precio: Double, duracion: Int, negocioId: Int): GenericResponse {
        val request = ServicioDto(id, nombre, precio.toFloat(), duracion, negocioId)
        return apiService.updateServicio(id, request)
    }

    override suspend fun deleteServicio(id: Int): GenericResponse {
        return apiService.deleteServicio(id)
    }

    override suspend fun getHistorialCitas(negocioId: Int): HistorialCitasResponse {
        return apiService.getHistorialCitas(negocioId)
    }
}
