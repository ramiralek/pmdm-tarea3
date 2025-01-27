plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {

    namespace = "condeceron.beatriz.pmdmtarea3"
    compileSdk = 35

    defaultConfig {
        applicationId = "condeceron.beatriz.pmdmtarea3"
        minSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation(libs.core.splashscreen)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.converter.scalars)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}