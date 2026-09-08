package com.example.tarea_082426.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea_082426.data.AuthRepository
import com.example.tarea_082426.model.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    fun loadProfile(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val userResult = repository.getUser(id)
            val profileResult = repository.getProfile(id)

            if (userResult.isSuccess && profileResult.isSuccess) {
                val userData = userResult.getOrNull()
                val profileBody = profileResult.getOrNull()?.body

                _state.value = _state.value.copy(
                    isLoading = false,
                    id = id,
                    userId = id,
                    nombre = userData?.get("nombre")?.toString() ?: "",
                    apellido = userData?.get("apellido")?.toString() ?: "",
                    usuario = userData?.get("usuario")?.toString() ?: "",
                    fotoBase64 = profileBody?.fotoBase64 ?: "",
                    telefono = profileBody?.telefono ?: "",
                    correo = userData?.get("email")?.toString() ?: "",
                    fechaNac = profileBody?.fechaNac ?: "",
                    genero = profileBody?.genero ?: ""
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error al cargar datos"
                )
            }
        }
    }
}
