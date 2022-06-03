plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

val tmp = 4

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.response)
                api(projects.pimonitorMonitorApiCore)
                api(projects.bitframeApiKtor)
                api(projects.authenticatorApiKtor)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.cache.mock)
                api(asoft.expect.coroutines)
            }
        }
    }
}