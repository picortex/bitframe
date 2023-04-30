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
        withJava()
    }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
//                api(asoft.kotlinx.serialization.mapper)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeActorUser)
                implementation(projects.kommanderCoroutines)
//                implementation(projects.kommanderCoroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
                api(kmongo.core.serialization)
                api(kmongo.coroutines.serialization)
                api(projects.koncurrentLaterCore)
//                api(asoft.koncurrent.later.core)
            }
        }

    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)