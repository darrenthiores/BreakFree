plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.binus.core_ui"
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(DateTime.dateTime)
}