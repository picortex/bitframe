plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
    signing
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

                api(projects.kronoApi)
                api(projects.koncurrentLaterCore)

                api(kotlinx.coroutines.core)
                api(asoft.platform.core)
                api(asoft.logging.console)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
//                implementation(asoft.expect.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.mailerSmtp)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)
