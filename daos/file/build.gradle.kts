plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
//    id("picortex-publish")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
                api(kotlinx.serialization.json)
                api(squareup.okio.core)
                api(projects.koncurrentExecutorsMock)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(squareup.okio.fake)
                implementation(projects.koncurrentLaterTest)
                implementation(projects.kommanderCoroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)