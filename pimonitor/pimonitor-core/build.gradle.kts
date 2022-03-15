plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeAuthenticationCore)
                api(projects.presentersCore)
                api(asoft.kotlinx.serialization.mapper) // PiMonitor API Needs to be parsed
                api(ktor.client.core) // We need to make rest calls to picortex server
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
                api(projects.presentersCore)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(ktor.client.cio)
            }
        }
    }
}