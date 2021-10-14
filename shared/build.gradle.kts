import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")

}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation(Develop.Ktor.commonCore)
                implementation(Develop.Ktor.commonJson)
                implementation(Develop.Ktor.commonLogging)
                implementation(Develop.Ktor.commonSerialization)
                implementation(Develop.koinCore)
                implementation(Develop.multiplatformSettings)
                implementation(Develop.SqlDelight.runtime)
                implementation(Develop.SqlDelight.coroutinesExtensions)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(Develop.koinTest)
                implementation(Develop.multiplatformSettingsTest)
                implementation(Develop.Ktor.commonTest)
                implementation(Develop.Ktor.cio)
                implementation("org.jetbrains.kotlin:kotlin-test")


            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Develop.Ktor.androidCore)
                implementation(Develop.SqlDelight.driverAndroid)

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation(Develop.Coroutines.test)
                implementation(Develop.KotlinTest.jvm)
                implementation(Develop.KotlinTest.junit)
                implementation(Develop.AndroidXTest.core)
                implementation(Develop.AndroidXTest.junit)
                implementation(Develop.AndroidXTest.runner)
                implementation(Develop.AndroidXTest.rules)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Develop.Ktor.ios)
                implementation(Develop.SqlDelight.driverIos)

            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
}

sqldelight {
    database("CafeDB") {
        packageName = "com.officeyuli.kmmexampleforitfinal"
    }
}