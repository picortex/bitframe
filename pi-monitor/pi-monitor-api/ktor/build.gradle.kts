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
                api(projects.piMonitorApiCore)
                api(projects.bitframeAuthenticationApiKtor)
                api(projects.piMonitorServiceClientKtor)
                api(asoft.cache.mock)
                api(asoft.logging.console)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }

        val jsMain by getting {
            dependencies {
                api(asoft.cache.browser)
                api(asoft.cache.react.native)
                api(kotlinx.coroutines.core)
            }
        }
    }
}