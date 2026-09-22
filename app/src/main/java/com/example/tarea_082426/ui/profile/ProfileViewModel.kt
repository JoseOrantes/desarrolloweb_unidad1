package com.example.tarea_082426.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarea_082426.model.ProfileState
import com.example.tarea_082426.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = ProfileRepository()

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    fun loadProfile(id: Int, context: Context) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val userResult = repository.getUserOfflineFirst(context, id)
            val profileResult = repository.getProfile(id)

            if (userResult.isSuccess && profileResult.isSuccess) {
                val userLocal = userResult.getOrNull()
                val profileBody = profileResult.getOrNull()?.body

                _state.value = _state.value.copy(
                    isLoading = false,
                    id = id,
                    userId = id,
                    nombre = userLocal?.nombre ?: "",
                    apellido = userLocal?.apellido ?: "",
                    usuario = userLocal?.id_user?.toString() ?: "", // O usar usuario si se agrega a la entidad User
                    fotoBase64 = profileBody?.fotoBase64 ?: "",
                    telefono = profileBody?.telefono ?: "",
                    correo = userLocal?.nombre ?: "", // Nota: Ajustar según campos disponibles en User entity
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
