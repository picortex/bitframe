plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.response)
                api(projects.bitframeAuthenticationSdkServerCore)
                api(projects.bitframeServiceGenericSdkServerCore)
                api(projects.bitframeSdkServerCore)
                api(projects.bitframeEventsInmemory)
                api(asoft.cache.mock)
                api(asoft.kotlinx.serialization.mapper)
                api(asoft.logging.console)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.cache.mock)
                implementation(projects.bitframeServerFrameworkTest)
                implementation(projects.bitframeEventsInmemory)
                implementation(kotlinx.serialization.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
    }
}