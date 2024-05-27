package com.hgm.streamvideocompose

import android.app.Application
import com.hgm.streamvideocompose.di.appModule
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

      private var currentName: String? = null
      var client: StreamVideo? = null

      override fun onCreate() {
            super.onCreate()
            startKoin {
                  androidContext(this@App)
                  modules(appModule)
            }
      }

      fun initVideoClient(username: String) {
            if (client == null || username != currentName) {
                  StreamVideo.removeClient()

                  // 重新初始化
                  client = StreamVideoBuilder(
                        context = this,
                        apiKey = "6zmpqsg7zvj9",
                        user = User(
                              id = username,
                              name = username,
                              type = UserType.Guest
                        )
                  ).build()
            }
      }
}