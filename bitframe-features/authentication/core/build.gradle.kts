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
                api(projects.bitframeServiceConfigCore)
                api(projects.eventsCore)
                api(projects.validation)
                api(asoft.cache.api)
                api(asoft.live.core)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
                implementation(projects.bitframeAuthenticationApiMock)
            }
        }
    }
}
