package com.ssk.vibeplayer.feature.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.vibeplayer.core.permission.PermissionChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor(
    private val permissionChecker: PermissionChecker
) : ViewModel() {

    private val _permissionState = MutableStateFlow(PermissionState())
    val permissionState = _permissionState
        .onStart {
            checkInitialPermission()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PermissionState()
        )

    private val _permissionEvents = Channel<PermissionEvent>()
    val permissionEvents = _permissionEvents.receiveAsFlow()

    private fun checkInitialPermission() {
        if (permissionChecker.hasAudioPermission()) {
            _permissionState.update { permissionState ->
                permissionState.copy(
                    status = PermissionStatus.GRANTED
                )
            }
            _permissionEvents.trySend(PermissionEvent.NavigateToLibrary)
        }
    }

    fun onAction(action: PermissionAction) {
        when (action) {
            PermissionAction.OnAllowAccess -> {
                _permissionEvents.trySend(PermissionEvent.RequestPermission)
            }

            is PermissionAction.OnPermissionResult -> {
                if (action.granted) {
                    _permissionState.update { it.copy(status = PermissionStatus.GRANTED) }
                    _permissionEvents.trySend(PermissionEvent.NavigateToLibrary)
                } else {
                    _permissionState.update {
                        it.copy(
                            status = PermissionStatus.DENIED,
                            showDeniedDialog = true,
                            denialCount = it.denialCount + 1
                        )
                    }
                }
            }

            PermissionAction.OnDeniedDialogDismiss -> {
                _permissionState.update { it.copy(showDeniedDialog = false) }
            }

            PermissionAction.OnDeniedDialogRetry -> {
                _permissionState.update { it.copy(showDeniedDialog = false) }
                _permissionEvents.trySend(PermissionEvent.RequestPermission)
            }

            PermissionAction.OnOpenSettings -> {
                _permissionState.update { it.copy(showDeniedDialog = false) }
                _permissionEvents.trySend(PermissionEvent.OpenSettings)
            }
        }
    }
}