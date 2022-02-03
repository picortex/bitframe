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
                api(projects.piMonitorDashboardIntegrationCore)
                api(ktor.client.core)
                api(asoft.kotlinx.serialization.mapper)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }

        val jvmTest by getting {
            dependencies {
                api(ktor.client.cio)
            }
        }
    }
}