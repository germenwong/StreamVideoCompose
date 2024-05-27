package com.hgm.streamvideocompose.di

import com.hgm.streamvideocompose.App
import com.hgm.streamvideocompose.connect.ConnectViewModel
import com.hgm.streamvideocompose.video.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
      // 这里不使用 single 原因在于client依赖于username，需要在运行时创建
      // 由于 single 在应用启动后就不会在执行，而 factory 在需要注入的地方都会重新执行，然后注入
      factory {
            val app=androidContext().applicationContext as App
            app.client
      }

      viewModelOf(::ConnectViewModel)
      viewModelOf(::VideoViewModel)
}