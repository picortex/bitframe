plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

object vers {
    val kotlin = "1.6.20-RC2"
    val ktor = "1.6.8"
    val foundation = "1.4.62"
}

dependencies {
    implementation("tz.co.asoft:foundation-plugins:${vers.foundation}")
    implementation("io.ktor:ktor-client-cio:${vers.ktor}")
    implementation("tz.co.asoft:kotlinx-serialization-mapper:${vers.foundation}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${vers.kotlin}")
}