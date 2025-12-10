package ortiz.derek.c4.barber_shop.data.remote.dto

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: AdminDto
)
