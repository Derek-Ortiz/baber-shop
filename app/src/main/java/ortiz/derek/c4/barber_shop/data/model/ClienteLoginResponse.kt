package ortiz.derek.c4.barber_shop.data.model

import ortiz.derek.c4.barber_shop.Models.Cliente

data class ClienteLoginResponse(
    val success: Boolean,
    val message: String,
    val cliente: Cliente? = null
)
