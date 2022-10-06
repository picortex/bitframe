plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
//    id("picortex-publish")
}

kotlin {
    js(IR) { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeServiceBuilderSdkClientCore)
                api(projects.liveReact)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)