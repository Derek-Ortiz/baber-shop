package ortiz.derek.c4.barber_shop.domain.repository

import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.Cliente
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.model.Negocio
import ortiz.derek.c4.barber_shop.data.model.Servicio

interface BarberShopRepository {
    suspend fun getNegocios(): List<Negocio>
    suspend fun getServicios(negocioId: Int): List<Servicio>
    suspend fun registrarCliente(request: ClienteRequest): Cliente
    suspend fun crearCita(request: CitaRequest): Cita
    suspend fun getCitasCliente(clienteId: Int): List<Cita>
}
