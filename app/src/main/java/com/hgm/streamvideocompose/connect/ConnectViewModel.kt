package com.hgm.streamvideocompose.connect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hgm.streamvideocompose.App
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ConnectViewModel(
      private val app: Application
) : AndroidViewModel(app) {

      private val _state = MutableStateFlow(ConnectState())
      val state = _state.asStateFlow()

      fun onAction(action: ConnectAction) {
            when (action) {
                  is ConnectAction.OnConnectClick -> connectRoom()
                  is ConnectAction.OnNameChange -> {
                        _state.update { it.copy(name = action.name) }
                  }
            }
      }

      private fun connectRoom(){
            _state.update { it.copy(errorMessage = null) }
            if (_state.value.name.isBlank()){
                  _state.update { it.copy(errorMessage = "The username can't be blank") }
                  return
            }

            (app as App).initVideoClient(_state.value.name)

            _state.update { it.copy(isConnected = true) }
      }
}