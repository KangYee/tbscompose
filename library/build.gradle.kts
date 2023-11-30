@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "io.github.kangyee.tbscompose.library"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    api(libs.tbs)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity.compose)
    implementation(libs.compose.foundation)
    implementation(platform(libs.compose.bom))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

mavenPublishing {
    coordinates("io.github.kangyee", "tbscompose", "1.0.1")

    pom {
        name.set("TbsCompose")
        description.set("TBS for Jetpack Compose")
        inceptionYear.set("2023")
        url.set("https://github.com/kangyee/tbscompose/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("kangyee")
                name.set("KangYee")
                url.set("https://github.com/kangyee/")
            }
        }
        scm {
            url.set("https://github.com/kangyee/tbscompose/")
            connection.set("scm:git:git://github.com/kangyee/tbscompose.git")
            developerConnection.set("scm:git:ssh://git@github.com/kangyee/tbscompose.git")
        }
    }

    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01, true)
    signAllPublications()
}