plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies {
    implementation("gradle.plugin.tz.co.asoft:foundation-plugins:1.4.0")
    implementation("io.ktor:ktor-client-cio:1.6.0")
    implementation("tz.co.asoft:kotlinx-serialization-mapper:0.0.71")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
}