package ortiz.derek.c4.barber_shop.data.remote.dto

data class CreateNegocioResponse(
    val success: Boolean,
    val message: String,
    val data: NegocioDto
)

data class NegocioDto(
    val id: Int,
    val nombreN: String,
    val direccion: String
)
