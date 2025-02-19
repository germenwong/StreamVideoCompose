plugins {
      alias(libs.plugins.android.application)
      alias(libs.plugins.jetbrains.kotlin.android)
      alias(libs.plugins.kotlin.serialization)
}

android {
      namespace = "com.hgm.streamvideocompose"
      compileSdk = 34

      defaultConfig {
            applicationId = "com.hgm.streamvideocompose"
            minSdk = 26
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                  useSupportLibrary = true
            }
      }

      buildTypes {
            release {
                  isMinifyEnabled = false
                  proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                  )
            }
      }
      compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
      }
      kotlinOptions {
            jvmTarget = "1.8"
      }
      buildFeatures {
            compose = true
      }
      composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
      }
      packaging {
            resources {
                  excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
      }
}

dependencies {
      implementation(libs.androidx.core.ktx)
      implementation(libs.androidx.lifecycle.runtime.ktx)
      implementation(libs.androidx.activity.compose)
      implementation(platform(libs.androidx.compose.bom))
      implementation(libs.androidx.compose.ui)
      implementation(libs.androidx.compose.ui.graphics)
      implementation(libs.androidx.compose.ui.tooling.preview)
      implementation(libs.androidx.compose.material3)
      testImplementation(libs.junit)
      androidTestImplementation(libs.androidx.junit)
      androidTestImplementation(libs.androidx.espresso.core)
      androidTestImplementation(platform(libs.androidx.compose.bom))
      androidTestImplementation(libs.androidx.compose.ui.test.junit4)
      debugImplementation(libs.androidx.compose.ui.tooling)
      debugImplementation(libs.androidx.compose.ui.test.manifest)
      implementation(libs.androidx.lifecycle.viewmodel.compose)

      implementation(libs.timber)
      implementation(libs.bundles.koin)
      implementation(libs.bundles.stream)
      implementation(libs.navigation.compose)
      implementation(libs.kotlinx.serialization.json)
}