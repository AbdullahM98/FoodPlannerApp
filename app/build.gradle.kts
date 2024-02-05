plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodplannerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodplannerapp"
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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.7.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.2")
    implementation ("com.squareup.okhttp3:okhttp:3.6.0")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    // Glide v4 uses this new annotation processor -- see https://bumptech.github.io/glide/doc/generatedapi.html
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    //room
    implementation("androidx.room:room-runtime:2.4.1")
    annotationProcessor("androidx.room:room-compiler:2.4.1")
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")
    implementation ("com.airbnb.android:lottie:3.4.0")
    implementation ("com.makeramen:roundedimageview:2.3.0")
    implementation ("androidx.room:room-runtime:2.4.1")
    annotationProcessor ("androidx.room:room-compiler:2.4.1")
}