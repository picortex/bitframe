plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeServiceBuilderCore)
                api(kotlinx.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
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