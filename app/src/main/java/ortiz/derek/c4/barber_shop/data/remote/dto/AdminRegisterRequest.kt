package ortiz.derek.c4.barber_shop.data.remote.dto

data class AdminRegisterRequest(
    val nombres: String,
    val apellidoP: String,
    val apellidoM: String,
    val telefono: String,
    val email: String,
    val contraseña: String,
    val negocioId: Int? = null
)
