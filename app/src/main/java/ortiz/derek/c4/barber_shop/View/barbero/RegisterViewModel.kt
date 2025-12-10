package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginResponse
import ortiz.derek.c4.barber_shop.data.remote.dto.RegisterRequest
import ortiz.derek.c4.barber_shop.domain.use_case.LoginUseCase
import ortiz.derek.c4.barber_shop.domain.use_case.RegisterUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _registerState = mutableStateOf<RegisterState>(RegisterState.Idle)
    val registerState: State<RegisterState> = _registerState

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val registerResponse = registerUseCase(registerRequest)
                if (registerResponse.success) {
                    val loginResponse = loginUseCase(LoginRequest(registerRequest.email, registerRequest.contraseña))
                    if (loginResponse.success) {
                        userPreferencesRepository.saveUserData(
                            userId = loginResponse.administrador.id,
                            email = registerRequest.email,
                            password = registerRequest.contraseña,
                            negocioId = loginResponse.administrador.negocioId
                        )
                        _registerState.value = RegisterState.Success(loginResponse)
                    } else {
                        _registerState.value = RegisterState.Error("Error al iniciar sesión después del registro")
                    }
                } else {
                    _registerState.value = RegisterState.Error("Error en el registro")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val loginResponse: LoginResponse) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
