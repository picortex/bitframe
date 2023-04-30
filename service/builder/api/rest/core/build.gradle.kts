plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeServiceBuilderApiCore)
                api(projects.bitframeServiceBuilderRest)

                api(projects.koncurrentExecutorsCoroutines)

                api(ktor.client.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCore)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(ktor.client.cio)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(), description = "A kotlin multiplatform library"
)