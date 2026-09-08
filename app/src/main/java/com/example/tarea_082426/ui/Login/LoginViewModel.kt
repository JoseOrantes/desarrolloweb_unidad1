package com.example.tarea_082426.ui.Login

import androidx.lifecycle.ViewModel
import com.example.tarea_082426.data.AuthRepository
import com.example.tarea_082426.model.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _state = MutableStateFlow(LoginState())

    val state: StateFlow<LoginState> = _state

    fun onEmailChange(nuevoEmail: String){
        _state.value = _state.value.copy(email = nuevoEmail)
    }

    fun onPasswordChange(nuevoPassword: String){
        _state.value = _state.value.copy(password = nuevoPassword)
    }

    //Parametros vienen del modelo (dto)
    fun onLoginClick(){
        val resultado = repository.login(_state.value.email, _state.value.password)
        _state.value = _state.value.copy(
            mensaje = if (resultado) "Bienvenido!" else "Creenciales Incorrectos",
            loginExitoso = resultado
        )
    }
}