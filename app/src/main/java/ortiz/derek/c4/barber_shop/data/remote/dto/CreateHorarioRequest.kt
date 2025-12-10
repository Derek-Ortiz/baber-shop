package ortiz.derek.c4.barber_shop.data.remote.dto

data class CreateHorarioRequest(
    val dia: String,
    val horaApertura: String,
    val horaCierre: String,
    val negocioId: Int
)
