plugins {
    alias(libs.plugins.android.application) // Este es el plugin para aplicaciones de Android
}

android {
    namespace = "edu.upc.dsa.simplecalculatordsa"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.upc.dsa.simplecalculatordsa"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat) // Asegúrate de que esta línea se corresponda con tu archivo libs.versions.toml
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
