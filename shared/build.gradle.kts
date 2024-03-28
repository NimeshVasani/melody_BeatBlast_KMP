plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.23"
    id("kotlin-parcelize")
    id("org.jetbrains.compose")

}
val ktorVersion = extra["ktor.version"]

kotlin {

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"

            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    jvm("desktop")
    js(IR) {
        browser()
    }
    applyDefaultHierarchyTemplate()

//    cocoapods {
//        summary = "Some description for the Shared Module"
//        homepage = "Link to the Shared Module homepage"
//        version = "1.0"
//        ios.deploymentTarget = "14.1"
//        podfile = project.file("../iosApp/Podfile")
//    }

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.runtime)
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-json:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.image.loader)
            api(libs.decompose)
            api("com.arkivanov.decompose:extensions-compose-jetbrains:2.2.2-compose-experimental")
            implementation(libs.lifecycle)
        }
        androidMain {
            dependencies {
                implementation("androidx.media3:media3-exoplayer:1.3.0")
            }
        }

        desktopMain.dependencies {
            implementation(compose.desktop.common)
            implementation("uk.co.caprica:vlcj:4.8.2")
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation("io.ktor:ktor-client-js:2.3.8")
            implementation("io.ktor:ktor-client-json-js:2.2.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.nimesh.vasani.melodybeatblastkmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.firebase.database.ktx)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer)
}
