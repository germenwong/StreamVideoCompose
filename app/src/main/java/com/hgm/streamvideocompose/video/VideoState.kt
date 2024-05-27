package com.hgm.streamvideocompose.video

import io.getstream.video.android.core.Call

/**
 * @author：HGM
 * @created：2024/5/27 0027
 * @description：
 **/
data class VideoState(
      val call: Call,
      val callState: CallState? = null,
      val error: String? = null
)

enum class CallState {
      JOINING,
      ACTIVE,
      ENDED
}
