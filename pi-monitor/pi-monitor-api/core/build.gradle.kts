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
                api(projects.piMonitorServiceClientCore)
            }
        }

        val commonTest by getting {
            dependencies {
                api(projects.piMonitorApiMock)
                api(asoft.expect.coroutines)
            }
        }
    }
}