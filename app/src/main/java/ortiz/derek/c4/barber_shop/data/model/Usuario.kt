package ortiz.derek.c4.barber_shop.data.model

data class Usuario(
    val id: Int,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val telefono: String,
    val direccion: String,
    val email: String
)
