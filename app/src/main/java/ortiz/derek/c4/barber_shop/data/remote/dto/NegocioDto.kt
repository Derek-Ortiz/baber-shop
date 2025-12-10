package ortiz.derek.c4.barber_shop.data.remote.dto

data class NegocioDto(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val servicios: List<ServicioDto> = emptyList(),
    val horarios: List<HorarioDto> = emptyList()
)
