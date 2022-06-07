plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    implementation(asoft.foundation.plugins)
    implementation(asoft.kotlinx.serialization.mapper)
    implementation(kotlinz.gradle.plugin)
}