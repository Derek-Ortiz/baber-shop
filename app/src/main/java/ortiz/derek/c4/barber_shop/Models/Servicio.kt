package ortiz.derek.c4.barber_shop.Models

data class Servicio(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val duracion: Int,
    val negocioId: Int
)
