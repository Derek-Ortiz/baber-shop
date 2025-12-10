package ortiz.derek.c4.barber_shop.data.remote.dto

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val administrador: AdminDto
)

data class AdminDto(
    val id: Int,
    val nombres: String,
    val apellidoP: String,
    val apellidoM: String,
    val telefono: String,
    val email: String,
    val negocioId: Int? = null
)
