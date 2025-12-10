package ortiz.derek.c4.barber_shop.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.model.ClienteRequest
import ortiz.derek.c4.barber_shop.data.model.Usuario
import ortiz.derek.c4.barber_shop.domain.repository.BarberShopRepository
import ortiz.derek.c4.barber_shop.view_models.state.RegistroState
import javax.inject.Inject

@HiltViewModel
class RegistrarViewModel @Inject constructor(
    private val repository: BarberShopRepository
) : ViewModel() {

    private val _registroState = MutableStateFlow<RegistroState>(RegistroState.Idle)
    val registroState: StateFlow<RegistroState> = _registroState.asStateFlow()

    fun resetState() {
        _registroState.value = RegistroState.Idle
    }

    fun registrarUsuario(usuario: Usuario, contrasena: String) {
        viewModelScope.launch {
            _registroState.value = RegistroState.Loading
            try {
                val request = ClienteRequest(
                    nombre = usuario.nombre,
                    apellidoPaterno = usuario.apellidoPaterno,
                    apellidoMaterno = usuario.apellidoMaterno,
                    telefono = usuario.telefono,
                    direccion = usuario.direccion,
                    email = usuario.email,
                    contrasena = contrasena
                )
                val result = repository.registrarCliente(request)
                
                val usuarioResult = Usuario(
                    id = result.id,
                    nombre = result.nombre,
                    apellidoPaterno = usuario.apellidoPaterno,
                    apellidoMaterno = usuario.apellidoMaterno,
                    telefono = usuario.telefono,
                    direccion = usuario.direccion,
                    email = result.email
                )
                _registroState.value = RegistroState.Success(usuarioResult)
            } catch (e: Exception) {
                _registroState.value = RegistroState.Error(e.message ?: "Error desconocido al registrar el usuario")
            }
        }
    }
}
