package ortiz.derek.c4.barber_shop.view_models.state

import ortiz.derek.c4.barber_shop.data.model.Usuario

sealed class RegistroState {
    object Idle : RegistroState()
    object Loading : RegistroState()
    data class Success(val usuario: Usuario) : RegistroState()
    data class Error(val message: String) : RegistroState()
}
