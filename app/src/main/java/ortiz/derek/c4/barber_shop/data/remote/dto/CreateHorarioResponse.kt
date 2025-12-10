package ortiz.derek.c4.barber_shop.data.remote.dto

data class CreateHorarioResponse(
    val success: Boolean,
    val message: String,
    val data: HorarioDto
)

data class HorarioDto(
    val id: Int,
    val dia: String,
    val horaApertura: String,
    val horaCierre: String,
    val negocioId: Int
)
