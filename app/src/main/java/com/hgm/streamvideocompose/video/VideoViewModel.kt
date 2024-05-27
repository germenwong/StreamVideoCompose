package com.hgm.streamvideocompose.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class VideoViewModel(
      private val videoClient: StreamVideo
) : ViewModel() {

      private val _state = MutableStateFlow(
            VideoState(
                  call = videoClient.call("default", "main_room"),
            )
      )
      val state = _state.asStateFlow()

      fun onAction(action: VideoAction) {
            when (action) {
                  VideoAction.JoinCallClick -> joinCall()
                  VideoAction.OnDisconnectClick -> leaveCall()
            }
      }

      private fun joinCall() {
            if (_state.value.callState == CallState.ACTIVE) {
                  return
            }

            viewModelScope.launch {
                  _state.update {
                        it.copy(callState = CallState.JOINING)
                  }

                  val shouldCreate = videoClient
                        .queryCalls(filters = emptyMap())
                        .getOrNull()
                        ?.calls
                        ?.isEmpty() == true

                  _state.value.call.join(create = shouldCreate)
                        .onSuccess {
                              _state.update {
                                    it.copy(callState = CallState.ACTIVE, error = null)
                              }
                        }
                        .onError { error ->
                              _state.update {
                                    it.copy(callState = null, error = error.message)
                              }
                        }
            }
      }

      private fun leaveCall(){
            _state.value.call.leave()
            videoClient.logOut()
            _state.update { 
                  it.copy(callState = CallState.ENDED)
            }
      }
}