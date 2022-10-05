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
//                api(asoft.koncurrent.primitives.mock)
                api(projects.koncurrentPrimitivesMock)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(asoft.expect.coroutines)
                implementation(projects.expectCoroutines)
            }
        }
    }
}