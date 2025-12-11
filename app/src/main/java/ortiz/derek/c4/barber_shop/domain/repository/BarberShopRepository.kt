package ortiz.derek.c4.barber_shop.domain.repository

import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.*

interface BarberShopRepository {
    suspend fun getNegocios(): List<NegocioDto>
    suspend fun getServicios(negocioId: Int): GetServiciosResponse
    suspend fun registrarCliente(request: ClienteRequest): GenericResponse
    suspend fun crearCita(request: CitaRequest): AddCitaResponse
    suspend fun getCitasCliente(clienteId: Int): List<Cita>

    // Métodos para el barbero
    suspend fun getNegocio(id: Int): GetNegocioResponse
    suspend fun createNegocio(nombre: String, direccion: String): CreateNegocioResponse
    suspend fun addServicio(nombre: String, precio: Double, duracion: Int, negocioId: Int): AddServicioResponse
    suspend fun updateServicio(id: Int, nombre: String, precio: Double, duracion: Int, negocioId: Int): GenericResponse
    suspend fun deleteServicio(id: Int): GenericResponse
    suspend fun getHistorialCitas(negocioId: Int): HistorialCitasResponse
}
