package ortiz.derek.c4.barber_shop.View

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginResponse
import ortiz.derek.c4.barber_shop.domain.use_case.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _loginState = mutableStateOf<LoginState>(LoginState.Idle)
    val loginState: State<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val response = loginUseCase(LoginRequest(email, password))
                if (response.success) {
                    // Comprobar si la respuesta es un administrador o un cliente
                    response.administrador?.let {
                        userPreferencesRepository.saveAdminData(it)
                    }
                    response.cliente?.let {
                        userPreferencesRepository.saveClientData(it)
                    }
                    _loginState.value = LoginState.Success(response)
                } else {
                    _loginState.value = LoginState.Error(response.message ?: "Usuario o contraseña incorrectos")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val loginResponse: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}