package ortiz.derek.c4.barber_shop.data.remote.dto

data class CreateServicioRequest(
    val nombre: String,
    val precio: Double,
    val duracion: Int,
    val negocioId: Int
)

data class UpdateServicioRequest(
    val nombre: String,
    val precio: Double,
    val duracion: Int,
    val negocioId: Int
)
