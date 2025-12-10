package ortiz.derek.c4.barber_shop.data.model

data class Servicio(
    val id: Int,
    val nombre: String,
    val descripcion: String? = null,
    val duracion: Int, // En minutos
    val precio: Double,
    val negocioId: Int
)
