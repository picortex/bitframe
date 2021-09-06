plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
}

repositories {
    publicRepos()
}

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                implementation("com.squareup:javapoet:${vers.kotlinpoet}")
                implementation("com.google.devtools.ksp:symbol-processing-api:${vers.ksp}")
                implementation(project(":bitframe-annotations"))
            }
        }
    }
}