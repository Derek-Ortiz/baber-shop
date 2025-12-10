package ortiz.derek.c4.barber_shop.data.remote.dto

import ortiz.derek.c4.barber_shop.Models.Servicio

data class ServicioDto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val duracion: Int,
    val negocioId: Int
) {
    fun toServicio() = Servicio(
        id = id,
        nombre = nombre,
        precio = precio,
        duracion = duracion,
        negocioId = negocioId
    )
}
