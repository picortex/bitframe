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
                api(projects.keepMock) // because we need a default ApiConfigDefault.cache
                api(projects.bitframeServiceBuilderCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)