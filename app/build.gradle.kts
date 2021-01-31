plugins {
    id(BuildPlugins.androidApp)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.daggerHilt)
}
android {
    compileSdkVersion(Versions.AndroidSdk.compileSdk)
    buildToolsVersion(Versions.AndroidSdk.buildTools)

    defaultConfig {
        applicationId = BuildParameters.applicationId
        minSdkVersion(Versions.AndroidSdk.minSdk)
        targetSdkVersion(Versions.AndroidSdk.targetSdk)
        versionCode = Versions.AndroidSdk.versionCode
        versionName = Versions.AndroidSdk.versionName
    }

    lintOptions.isAbortOnError = false

    signingConfigs {
        maybeCreate(BuildType.release).apply {
            storeFile = file(SigningConfigs.storeFile)
            keyAlias = SigningConfigs.keyAlias
            keyPassword = SigningConfigs.keyPassword
            storePassword = SigningConfigs.storePassword
        }
    }

    buildTypes {
        getByName(BuildType.debug) {
            buildConfigField("String", "API_BASE_URL", Configs.apiBaseUrl)
            buildConfigField("String", "DEFAULT_COUNTRY_CODE", Configs.defaultCountryCode)
        }

        getByName(BuildType.release) {
            buildConfigField("String", "API_BASE_URL", Configs.apiBaseUrl)
            buildConfigField("String", "DEFAULT_COUNTRY_CODE", Configs.defaultCountryCode)

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName(BuildType.release)

            proguardFiles(
                getDefaultProguardFile(BuildParameters.proguardAndroidFile),
                BuildParameters.proguardRules
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    androidExtensions.isExperimental = true
    buildFeatures.viewBinding = true
}

dependencies {

    // Libraries AndroidX
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.activity_ktx)
    implementation(Libs.AndroidX.fragment_ktx)
    implementation(Libs.AndroidX.navigation_fragment)
    implementation(Libs.AndroidX.navigation_ui)

    // Libraries Other
    implementation(Libs.Other.hilt)
    implementation(Libs.Other.hilt_viewmodel)
    implementation(Libs.Other.okhttp3_logging)
    implementation(Libs.Other.retrofit2)
    implementation(Libs.Other.retrofit2_converter)
    implementation(Libs.Other.timber)
    implementation(Libs.Other.material_design)
    implementation(Libs.Other.cardview)
    implementation(Libs.Other.recyclerview)
    implementation(Libs.Other.swiperefreshlayout)
    implementation(Libs.Other.glide)
    implementation(Libs.Other.multistatetogglebutton)

    // Kapt
    kapt(Libs.Kapt.hilt)
    kapt(Libs.Kapt.hilt_viewmodel)
    kapt(Libs.Kapt.glide)

    // AnnotationProcessor
    annotationProcessor(Libs.AnnotationProcessor.glide)
}