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
                api(projects.bitframeActorCore)
                api(projects.identifierComm)
                api(projects.geoCore)
                api(projects.kroneckerCore)
                api(projects.koncurrentLaterCore)
                api(projects.kollectionsInteroperable)
                api(projects.kronoKotlinx)
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