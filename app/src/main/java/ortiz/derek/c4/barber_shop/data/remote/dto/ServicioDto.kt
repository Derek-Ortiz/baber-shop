package ortiz.derek.c4.barber_shop.data.remote.dto

data class ServicioDto(
    val id: Int,
    val nombre: String,
    val precio: Float,
    val duracion: Int,
    val negocioId: Int
)
