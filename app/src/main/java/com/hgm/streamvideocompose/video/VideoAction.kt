package com.hgm.streamvideocompose.video

/**
 * @author：HGM
 * @created：2024/5/27 0027
 * @description：
 **/
sealed interface VideoAction {
      object OnDisconnectClick : VideoAction
      object JoinCallClick : VideoAction
}