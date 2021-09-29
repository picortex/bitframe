plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }
    js(IR) { browserLib() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-annotations-core"))
                api(project(":bitframe-authentication-service-core"))
            }
        }
    }
}