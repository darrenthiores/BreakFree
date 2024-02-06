plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.binus.register"
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))

    "implementation"(Coil.coilCompose)
    "implementation"(Accompanist.viewPager)
}