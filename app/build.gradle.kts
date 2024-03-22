plugins {
    id("com.android.application")
}

android {
    namespace = "com.techinnovation.nigerianbankcodes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.techinnovation.nigerianbankcodes"
        minSdk = 26
        targetSdk = 34
        versionCode = 11
        versionName = "1.4.8"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.etebarian:meow-bottom-navigation:1.2.0")
    ///apero Ads
    implementation ("apero-inhouse:apero-ads:1.8.0-alpha01")
    //billing
    implementation ("com.android.billingclient:billing:6.1.0")

}