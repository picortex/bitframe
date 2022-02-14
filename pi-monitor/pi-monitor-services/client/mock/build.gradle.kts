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
                api(projects.bitframeServiceConfigApiMock)
                api(projects.bitframeAuthenticationApiMock)
                api(projects.piMonitorServiceClientCore)
                api(projects.piMonitorServiceDaod)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.cache.mock)
                api(asoft.expect.coroutines)
//                api(projects.piMonitorTestTesting)
            }
        }
    }
}