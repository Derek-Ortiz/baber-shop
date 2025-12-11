package ortiz.derek.c4.barber_shop.data.remote.dto

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: Data? = null
) {
    data class Data(
        val administrador: Administrador? = null,
        val cliente: ClienteDto? = null
    )

    data class Administrador(
        val id: Int,
        val nombres: String,
        val apellidoP: String,
        val apellidoM: String,
        val telefono: String,
        val email: String,
        val negocioId: Int?
    )
}