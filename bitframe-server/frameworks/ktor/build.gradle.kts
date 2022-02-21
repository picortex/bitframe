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
                api(projects.bitframeServerFrameworkCore)
                api(ktor.server.cio)
                api(ktor.server.cors)
            }
        }
    }
}