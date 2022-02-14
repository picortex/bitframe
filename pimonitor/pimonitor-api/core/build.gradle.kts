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
                api(projects.pimonitorCore)
                api(projects.bitframeSdkClientCore)
//                api(projects.bitframeEventsInmemory)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
//                api(kotlinx.serialization.json)
                api(projects.pimonitorApiMock)
            }
        }
    }
}