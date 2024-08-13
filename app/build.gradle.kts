plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.openclassrooms.notes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.openclassrooms.notes"
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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.activity:activity-compose:1.9.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.4")

    implementation("io.insert-koin:koin-androidx-compose:4.0.0-RC1")

    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    // To use Kotlin annotation processing tool (kapt)
    //kapt("androidx.room:room-compiler:2.6.1")
    // To use Kotlin Symbol Processing (KSP)
    //ksp("androidx.room:room-compiler:2.6.1")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core-ktx:1.6.1")
    testImplementation("androidx.test.ext:junit-ktx:1.2.1")
    testImplementation("androidx.test:runner:1.6.1")
    testImplementation("io.insert-koin:koin-test-junit4:4.0.0-RC1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("com.google.truth:truth:1.1.2")
    testImplementation("org.robolectric:robolectric:4.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")




   // androidTestImplementation("androidx.test:runner:1.6.1")
    //androidTestImplementation("androidx.test:core-ktx:1.6.1")
   // androidTestImplementation("androidx.test.ext:junit:1.2.1")
  //  androidTestImplementation("androidx.test.ext:junit-ktx:1.2.1")
   // androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
   // androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
   // androidTestImplementation("androidx.test:rules:1.6.1")

}