plugins {
    `kotlin-dsl`
//    id("java-gradle-plugin")
//    id("maven-publish")
//    id("com.gradle.plugin-publish") version "0.18.0"
}

//pluginBundle {
//    website = "https://picortex.com"
//    vcsUrl = "https://github.com/picortex/bitframe"
//    tags = listOf("devops")
//}
//
//gradlePlugin {
//    group = "com.picortex"
//    version = bitframe.versions.current.get()
//
//    plugins {
//        val dockerCompose by creating {
//            id = "docker-compose"
//            displayName = "Docker Compose"
//            description = "User docker compose with gradle"
//            implementationClass = "docker.DockerComposePlugin"
//        }
//    }
//}
//
//publishing {
//    repositories {
//        maven {
//            name = "buildDirRepo"
//            url = file("build/repo").toURI() // uri("../local-plugin-repository")
//        }
//    }
//}

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