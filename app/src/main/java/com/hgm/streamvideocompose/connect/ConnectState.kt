package com.hgm.streamvideocompose.connect

/**
 * @author：HGM
 * @created：2024/5/27 0027
 * @description：连接状态
 **/
data class ConnectState(
      val name: String = "",
      val isConnected: Boolean = false,
      val errorMessage: String? = null
)
