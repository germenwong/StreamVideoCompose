package com.hgm.streamvideocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hgm.streamvideocompose.connect.ConnectScreen
import com.hgm.streamvideocompose.connect.ConnectViewModel
import com.hgm.streamvideocompose.ui.theme.StreamVideoComposeTheme
import com.hgm.streamvideocompose.video.CallState
import com.hgm.streamvideocompose.video.VideoScreen
import com.hgm.streamvideocompose.video.VideoViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                  StreamVideoComposeTheme {
                        Scaffold(
                              modifier = Modifier.fillMaxSize()
                        ) { innerPadding ->
                              val navController = rememberNavController()
                              NavHost(
                                    navController = navController,
                                    startDestination = ConnectRoute,
                                    modifier = Modifier.padding(innerPadding)
                              ) {
                                    composable<ConnectRoute> {
                                          val viewModel = koinViewModel<ConnectViewModel>()
                                          val state by viewModel.state.collectAsState()

                                          LaunchedEffect(key1 = state.isConnected) {
                                                if (state.isConnected) {
                                                      navController.navigate(VideoRoute) {
                                                            popUpTo(ConnectRoute) {
                                                                  inclusive = true
                                                            }
                                                      }
                                                }
                                          }

                                          ConnectScreen(
                                                state = state,
                                                onAction = viewModel::onAction
                                          )
                                    }

                                    composable<VideoRoute> {
                                          val viewModel = koinViewModel<VideoViewModel>()
                                          val state by viewModel.state.collectAsState()

                                          LaunchedEffect(key1 = state.callState) {
                                                if (state.callState == CallState.ENDED) {
                                                      navController.navigate(ConnectRoute) {
                                                            popUpTo(VideoRoute) {
                                                                  inclusive = true
                                                            }
                                                      }
                                                }
                                          }

                                          VideoScreen(
                                                state = state,
                                                onAction = viewModel::onAction
                                          )
                                    }
                              }
                        }
                  }
            }
      }
}

@Serializable
object ConnectRoute

@Serializable
object VideoRoute