package com.hgm.streamvideocompose.connect

/**
 * @author：HGM
 * @created：2024/5/27 0027
 * @description：
 **/
sealed interface ConnectAction {
      data class OnNameChange(val name: String) : ConnectAction
      object OnConnectClick : ConnectAction
}