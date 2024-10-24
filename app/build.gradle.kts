plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    
}

android {
    namespace = "com.example.testnach"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testnach"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            isJniDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("com.squareup.picasso:picasso:2.8") // Para cargar imágenes
    implementation ("com.github.bumptech.glide:glide:4.15.0")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    
    //implementation("com.google.firebase:firebase-auth")
    //implementation ("com.google.firebase:firebase-auth-ktx:24.5.0")
    implementation ("com.google.firebase:firebase-bom:32.0.0") // Usa el BOM para manejar versiones
    implementation ("com.google.firebase:firebase-auth-ktx")

    implementation(libs.androidx.appcompat)
    implementation(libs.firebase.auth.ktx)
}