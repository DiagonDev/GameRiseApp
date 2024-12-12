plugins {
    alias(libs.plugins.android.application)
    id("org.sonarqube") version "5.1.0.4882"
}

android {
    namespace = "xyz.brawl.gamerise"
    compileSdk = 34

    defaultConfig {
        applicationId = "xyz.brawl.gamerise"
        minSdk = 28
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
    sonar {
        properties {
            property("sonar.projectKey", "GameRise")
            property("sonar.host.url", "http://localhost:9000")
            //expires in 30 days from 30/11
            property("sonar.login", "sqp_9eed3e383bccccc71e6376e1a155356e50e069c0")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.room.common)
    testImplementation(libs.junit)
    implementation(libs.material.vversion)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.fragment)
    implementation(libs.mpandroidchart)
    implementation(libs.room.runtime)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.scalars)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
}