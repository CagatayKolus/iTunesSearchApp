object Configs {
    const val apiBaseUrl = "\"https://itunes.apple.com/\""
    const val defaultCountryCode = "\"tr\""
}

object BuildPlugins {
    const val androidApp = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinExtensions = "kotlin-android-extensions"
    const val daggerHilt = "dagger.hilt.android.plugin"
}

object BuildType {
    const val debug = "debug"
    const val release = "release"
}

object BuildParameters {
    const val applicationId = "com.cagataykolus.itunessearchapp"
    const val proguardFile = "proguard-android-optimize.txt"
    const val proguardAndroidFile = "proguard-android.txt"
    const val proguardRules = "proguard-rules.pro"
    const val proguardTestRules = "proguard-test-rules.pro"
}

object Plugins {
    const val manes_gradle_version = "com.github.ben-manes.versions"
}

object SigningConfigs {
    const val storeFile = "keystore.jks"
    const val keyAlias = "App Release Key"
    const val keyPassword = "123456"
    const val storePassword = "123456"
}

object Versions {
    object AndroidSdk {
        const val compileSdk = 30
        const val buildTools = "29.0.3"
        const val minSdk = 21
        const val targetSdk = 30
        const val versionCode = 1
        const val versionName = "0.1.0"
    }

    object Library {
        // AndroidX
        const val constraintlayout = "2.0.1"
        const val appcompat = "1.2.0"
        const val navigation_ui = "2.3.0"
        const val activity_ktx = "1.2.0-alpha05"
        const val fragment_ktx = "1.3.0-alpha05"
        const val arch_core = "2.1.0"
        const val coroutines = "1.3.7"

        // Other
        const val hilt = "2.28-alpha"
        const val hilt_viewmodel = "1.0.0-alpha01"
        const val okhttp3 = "4.9.0"
        const val retrofit2 = "2.9.0"
        const val timber = "4.7.1"
        const val material_design = "1.2.1"
        const val cardview = "1.0.0"
        const val recyclerview = "1.1.0"
        const val swiperefreshlayout = "1.1.0"
        const val glide = "4.11.0"
        const val multistatetogglebutton = "0.2.2"
    }
}

object Libs {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Library.appcompat}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.Library.constraintlayout}"
        const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Library.navigation_ui}"
        const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.Library.navigation_ui}"
        const val activity_ktx = "androidx.activity:activity-ktx:${Versions.Library.activity_ktx}"
        const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.Library.fragment_ktx}"
    }

    object Kapt {
        const val hilt = "com.google.dagger:hilt-android-compiler:${Versions.Library.hilt}"
        const val hilt_viewmodel = "androidx.hilt:hilt-compiler:${Versions.Library.hilt_viewmodel}"
        const val glide = "com.github.bumptech.glide:compiler:${Versions.Library.glide}"
    }

    object AnnotationProcessor {
        const val glide = "com.github.bumptech.glide:compiler:${Versions.Library.glide}"
    }

    object Other {
        const val hilt = "com.google.dagger:hilt-android:${Versions.Library.hilt}"
        const val hilt_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Library.hilt_viewmodel}"
        const val okhttp3_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.Library.okhttp3}"
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.Library.retrofit2}"
        const val retrofit2_converter = "com.squareup.retrofit2:converter-moshi:${Versions.Library.retrofit2}"
        const val timber = "com.jakewharton.timber:timber:${Versions.Library.timber}"
        const val material_design = "com.google.android.material:material:${Versions.Library.material_design}"
        const val cardview = "androidx.cardview:cardview:${Versions.Library.cardview}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.Library.recyclerview}"
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Library.swiperefreshlayout}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.Library.glide}"
        const val multistatetogglebutton = "org.honorato.multistatetogglebutton:multistatetogglebutton:${Versions.Library.multistatetogglebutton}"
    }
}