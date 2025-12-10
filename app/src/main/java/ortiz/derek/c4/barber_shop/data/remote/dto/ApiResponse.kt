package ortiz.derek.c4.barber_shop.data.remote.dto

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T
)
