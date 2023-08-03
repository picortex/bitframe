plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
//    id("picortex-publish")
    signing
}

kotlin {
    jvm {
        library()
    }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
//                api(asoft.kotlinx.serialization.mapper)
//                api(asoft.koncurrent.primitives.mock)
                api(projects.koncurrentExecutorsMock)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.kommanderCoroutines)
                implementation(projects.kommanderCoroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)