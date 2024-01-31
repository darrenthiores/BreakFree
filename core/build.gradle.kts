plugins {
    id("com.android.library")
    kotlin("plugin.serialization") version Kotlin.version
    id("kotlin-kapt")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.binus.core"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"0.0.0.0:8080\"")

        buildConfigField("String", "SQL_PASSWORD", "\"BREAKFREEYOURADDICTION\"")

        buildConfigField("String", "DATA_STORE_NAME", "\"DATA_MANAGEMENT\"")
        buildConfigField("String", "ENCRYPTED_PREF_NAME", "\"BREAKFREE_ENCRYPT\"")
        buildConfigField("String", "IS_LOGIN_KEY", "\"IS_LOGIN\"")
        buildConfigField("String", "USER_TOKEN_KEY", "\"USER_TOKEN\"")
        buildConfigField("String", "USER_REFRESH_TOKEN_KEY", "\"USER_REFRESH_TOKEN\"")
        buildConfigField("String", "RECORD_VIEW_KEY", "\"RECORD_VIEW\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    "implementation"(Ktor.ktorClientCore)
    "implementation"(Ktor.ktorClientAndroid)
    "implementation"(Ktor.ktorSerialization)
    "implementation"(Ktor.ktorSerializationJson)
    "implementation"(Ktor.ktorClientLogging)
    "implementation"(Ktor.ktorClientAuth)
    "implementation"(Ktor.logBackClassic)

    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
    "implementation"(Room.roomPaging)
    "kapt"(Room.roomCompiler)

    "implementation"(Room.sqliteChiper)
    "implementation"(Room.sqlite)

    "implementation"(AndroidX.encPreference)
    "implementation"(AndroidX.dataStore)
}