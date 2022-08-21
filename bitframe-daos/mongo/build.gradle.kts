plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("picortex-publish")
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
                api(asoft.kotlinx.serialization.mapper)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeActorUser)
                implementation(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
                api(kmongo.core.serialization)
                api(kmongo.coroutines.serialization)
                api(asoft.koncurrent.later.core)
            }
        }

    }
}