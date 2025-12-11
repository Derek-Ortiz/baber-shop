package ortiz.derek.c4.barber_shop.View.barbero

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ortiz.derek.c4.barber_shop.data.local.UserPreferencesRepository
import ortiz.derek.c4.barber_shop.data.remote.dto.AdminRegisterRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginRequest
import ortiz.derek.c4.barber_shop.data.remote.dto.LoginResponse
import ortiz.derek.c4.barber_shop.domain.repository.AdminRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val adminRepository: AdminRepository, // Usar el repositorio directamente para más claridad
    val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _registerState = mutableStateOf<RegisterState>(RegisterState.Idle)
    val registerState: State<RegisterState> = _registerState

    fun register(registerRequest: AdminRegisterRequest) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                // 1. Registrar al administrador
                val registerResponse = adminRepository.register(registerRequest)

                if (registerResponse.success) {
                    // 2. Si el registro es exitoso, iniciar sesión automáticamente
                    val loginResponse = adminRepository.login(LoginRequest(registerRequest.email, registerRequest.contraseña))

                    if (loginResponse.success) {
                        // 3. Guardar los datos del administrador y actualizar el estado a Success
                        loginResponse.administrador?.let {
                            userPreferencesRepository.saveAdminData(it)
                            _registerState.value = RegisterState.Success(loginResponse)
                        } ?: run {
                            // Este caso no debería ocurrir si el login es exitoso
                            _registerState.value = RegisterState.Error("Error: No se pudo obtener el administrador después del login")
                        }
                    } else {
                        _registerState.value = RegisterState.Error(loginResponse.message ?: "Error al iniciar sesión después del registro")
                    }
                } else {
                    _registerState.value = RegisterState.Error(registerResponse.message ?: "Error en el registro")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Error desconocido durante el registro")
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