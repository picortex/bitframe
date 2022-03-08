plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

val tmp = 1

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.pimonitorCore)
                api(projects.bitframeAuthenticationDaod)
                api(projects.akkountsSage) // we are integrating with sage one
                api(asoft.kotlinx.serialization.mapper) // PiMonitor API Needs to be parsed
                api(ktor.client.core) // We need to make rest calls to picortex server
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }
    }
}