plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-cio:1.6.0")
    implementation("tz.co.asoft:kotlinx-serialization-mapper:0.0.71")
}