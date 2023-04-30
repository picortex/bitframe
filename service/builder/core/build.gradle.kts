plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
//    id("picortex-publish")
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
                api(projects.habitatCore)
                api(projects.lexiConsole)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCore)
//                implementation(projects.kommanderCore)
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
