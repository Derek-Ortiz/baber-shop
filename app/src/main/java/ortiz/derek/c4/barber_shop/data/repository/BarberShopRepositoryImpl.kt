package ortiz.derek.c4.barber_shop.data.repository

import ortiz.derek.c4.barber_shop.data.model.Cita
import ortiz.derek.c4.barber_shop.data.model.CitaRequest
import ortiz.derek.c4.barber_shop.data.model.Cliente
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.model.Horario
import ortiz.derek.c4.barber_shop.data.model.Negocio
import ortiz.derek.c4.barber_shop.data.model.Servicio
import ortiz.derek.c4.barber_shop.data.remote.ApiService
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import javax.inject.Inject

class BarberShopRepositoryImpl @Inject constructor(private val apiService: ApiService) : BarberShopRepository {

    override suspend fun getNegocios(): List<Negocio> {
        val response = apiService.getNegocios()
        if (response.success) {
            return response.data.map { negocioDto ->
                Negocio(
                    id = negocioDto.id,
                    nombre = negocioDto.nombre,
                    direccion = negocioDto.direccion,
                    telefono = negocioDto.telefono,
                    servicios = negocioDto.servicios.map { servicioDto ->
                        Servicio(
                            id = servicioDto.id,
                            nombre = servicioDto.nombre,
                            precio = servicioDto.precio,
                            duracion = servicioDto.duracion
                        )
                    },
                    horarios = negocioDto.horarios.map { horarioDto ->
                        Horario(
                            id = horarioDto.id,
                            dia = horarioDto.dia,
                            horaApertura = horarioDto.horaApertura,
                            horaCierre = horarioDto.horaCierre,
                            negocioId = horarioDto.negocioId
                        )
                    }
                )
            }
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getServicios(negocioId: Int): List<Servicio> {
        val response = apiService.getNegocioDetalle(negocioId)
        if (response.success) {
            return response.data.servicios.map { servicioDto ->
                Servicio(
                    id = servicioDto.id,
                    nombre = servicioDto.nombre,
                    precio = servicioDto.precio,
                    duracion = servicioDto.duracion
                )
            }
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun registrarCliente(request: ClienteRequest): Cliente {
        val response = apiService.registrarCliente(request)
        if (response.success) {
            val clienteDto = response.data
            return Cliente(
                id = clienteDto.id,
                nombre = clienteDto.nombres,
                email = clienteDto.email
            )
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun crearCita(request: CitaRequest): Cita {
        val response = apiService.crearCita(request)
        if (response.success) {
            val citaDto = response.data
            return Cita(
                id = citaDto.id,
                fecha = citaDto.fecha,
                hora = citaDto.hora,
                estado = citaDto.estado,
                clienteId = citaDto.clienteId,
                servicioId = citaDto.servicioId,
                negocioId = citaDto.negocioId
            )
        } else {
            throw Exception(response.message)
        }
    }

    override suspend fun getCitasCliente(clienteId: Int): List<Cita> {
        val response = apiService.getCitasCliente(clienteId)
        if (response.success) {
            return response.data.map { citaDto ->
                Cita(
                    id = citaDto.id,
                    fecha = citaDto.fecha,
                    hora = citaDto.hora,
                    estado = citaDto.estado,
                    clienteId = citaDto.clienteId,
                    servicioId = citaDto.servicioId,
                    negocioId = citaDto.negocioId
                )
            }
        } else {
            throw Exception(response.message)
        }
    }
}
