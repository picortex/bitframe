plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    id("picortex-publish")
    signing
}

kotlin {
    target { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeSdkServerCore)
                api(ktor.server.cio)
                api(ktor.server.cors)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)