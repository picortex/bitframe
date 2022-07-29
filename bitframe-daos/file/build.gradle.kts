plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("picortex-publish")
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeDaoCore)
                api(kotlinx.serialization.json)
                api(asoft.koncurrent.primitives.mock)
                api(squareup.okio.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(squareup.okio.fake)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}