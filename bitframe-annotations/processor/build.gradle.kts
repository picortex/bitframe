plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("com.squareup:javapoet:${vers.kotlinpoet}")
                implementation("com.google.devtools.ksp:symbol-processing-api:${vers.ksp}")
                implementation(projects.bitframeAnnotationsCore)
            }
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }
    }
}