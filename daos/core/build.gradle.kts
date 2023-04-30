plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
//    id("picortex-publish")
    signing
}

val tmp = 0

kotlin {
    jvm {
        library()
        withJava()
    }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeActorCore)
//                api(asoft.kotlinx.serialization.mapper)
                api(kotlinx.coroutines.core)
//                api(kotlinx.datetime)
                api(projects.kronoApi)
                api(projects.koncurrentLaterCoroutines)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeDaoMock)
//                implementation(projects.kommanderCore)
                implementation(projects.kommanderCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)