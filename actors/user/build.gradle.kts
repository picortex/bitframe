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
                api(projects.bitframeActorCore)
                api(projects.kronoApi)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(asoft.expect.core)
                implementation(projects.expectCore)
            }
        }
    }
}


aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)