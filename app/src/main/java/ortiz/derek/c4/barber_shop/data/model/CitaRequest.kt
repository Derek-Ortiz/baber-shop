package ortiz.derek.c4.barber_shop.data.model

data class CitaRequest(
    val clienteId: Int,
    val negocioId: Int,
    val servicioId: Int,
    val fecha: String, // Formato YYYY-MM-DD
    val hora: String   // Formato HH:mm
)
