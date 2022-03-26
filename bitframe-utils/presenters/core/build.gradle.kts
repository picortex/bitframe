plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

val tmp = 2

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.core)
                api(asoft.viewmodel.core)
                api(asoft.kash.core)
                api(asoft.kotlinx.collections.interoperable)
                api(kotlinx.datetime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
                implementation(kotlinx.serialization.json)
                implementation(projects.presentersMock)
            }
        }
    }
}