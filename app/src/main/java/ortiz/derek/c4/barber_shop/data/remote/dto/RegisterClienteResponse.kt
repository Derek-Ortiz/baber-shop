package ortiz.derek.c4.barber_shop.data.remote.dto

data class RegisterClienteResponse(
    val success: Boolean,
    val message: String?,
    val data: ClienteDto
)
