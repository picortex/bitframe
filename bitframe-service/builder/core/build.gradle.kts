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
                api(projects.eventsInmemory)
                api(projects.bitframeActorUser)
                api(projects.bitframeActorSpace)
                api(projects.bitframeActorApp)
                api(kotlinx.coroutines.core)
                api(asoft.platform.core)
                api(asoft.logging.console)
                api(asoft.later.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.mailerSmtp)
            }
        }
    }
}
