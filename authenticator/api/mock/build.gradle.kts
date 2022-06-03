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
                api(projects.authenticatorApiCore)
                api(projects.authenticatorDaod)
                api(projects.bitframeApiMock)
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