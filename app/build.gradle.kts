import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serilization)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.razorquake.stoxx"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.razorquake.stoxx"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        //return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_KEY") ?: "demo"
        buildConfigField("String", "API_KEY", apiKey)

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.paging.compose)
    //Splash Api
    implementation(libs.androidx.core.splashscreen)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    //hilts
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)



    // For local unit tests
    //Coil
    implementation(libs.coil.compose)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //DataStore
    implementation(libs.androidx.datastore.preferences)
    //Compose Foundation
    implementation(libs.androidx.foundation)
    //Accompanist
    implementation(libs.accompanist.systemuicontroller)

}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
