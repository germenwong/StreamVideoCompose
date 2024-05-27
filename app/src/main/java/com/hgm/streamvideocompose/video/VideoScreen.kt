package com.hgm.streamvideocompose.video


import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.getstream.video.android.compose.permission.rememberCallPermissionsState
import io.getstream.video.android.compose.permission.rememberCameraPermissionState
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.controls.actions.DefaultOnCallActionHandler
import io.getstream.video.android.core.call.state.AcceptCall
import io.getstream.video.android.core.call.state.CancelCall
import io.getstream.video.android.core.call.state.ChatDialog
import io.getstream.video.android.core.call.state.ChooseLayout
import io.getstream.video.android.core.call.state.CustomAction
import io.getstream.video.android.core.call.state.DeclineCall
import io.getstream.video.android.core.call.state.FlipCamera
import io.getstream.video.android.core.call.state.InviteUsersToCall
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.call.state.Reaction
import io.getstream.video.android.core.call.state.SelectAudioDevice
import io.getstream.video.android.core.call.state.Settings
import io.getstream.video.android.core.call.state.ShowCallParticipantInfo
import io.getstream.video.android.core.call.state.ToggleCamera
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.core.call.state.ToggleScreenConfiguration
import io.getstream.video.android.core.call.state.ToggleSpeakerphone

@Composable
fun VideoScreen(
      state: VideoState,
      onAction: (VideoAction) -> Unit
) {
      when {
            state.error != null -> {
                  Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                  ) {
                        Text(text = state.error, color = MaterialTheme.colorScheme.error)
                  }
            }

            state.callState == CallState.JOINING -> {
                  Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                        CircularProgressIndicator()
                        Text(text = "Joining...")
                  }
            }

            else -> {
                  val basePermissions = listOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                  )
                  val bluetoothPermissions = if (Build.VERSION.SDK_INT >= 31) {
                        listOf(Manifest.permission.BLUETOOTH_CONNECT)
                  } else emptyList()
                  val notificationPermissions = if (Build.VERSION.SDK_INT >= 33) {
                        listOf(Manifest.permission.POST_NOTIFICATIONS)
                  } else emptyList()
                  val context = LocalContext.current
                  VideoTheme {
                        CallContent(
                              call = state.call,
                              modifier = Modifier.fillMaxSize(),
                              permissions = rememberCallPermissionsState(
                                    call = state.call,
                                    permissions = basePermissions + bluetoothPermissions + notificationPermissions,
                                    onPermissionsResult = { permissions ->
                                          if (permissions.values.contains(false)) {
                                                Toast.makeText(
                                                      context,
                                                      "请授予所有权限",
                                                      Toast.LENGTH_SHORT
                                                ).show()
                                          } else {
                                                onAction(VideoAction.JoinCallClick)
                                          }
                                    }
                              ),
                              onCallAction = { action ->
                                    if (action == LeaveCall) {
                                          onAction(VideoAction.OnDisconnectClick)
                                    }

                                    // 其他操作按照默认处理
                                    DefaultOnCallActionHandler.onCallAction(state.call, action)
                              },
                              onBackPressed = {
                                    onAction(VideoAction.OnDisconnectClick)
                              }
                        )
                  }
            }
      }
}