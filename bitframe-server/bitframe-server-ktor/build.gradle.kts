plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
}

kotlin {
    target { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-server-core"))
                api("io.ktor:ktor-server-cio:${vers.ktor}")
            }
        }
    }
}