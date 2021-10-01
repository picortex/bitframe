plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    target { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-server-framework-core"))
                api("io.ktor:ktor-server-cio:${vers.ktor}")
            }
        }
    }
}