plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
}

val tmp = 2

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.kronoApi)
                api(kotlinx.serialization.json)
                api(asoft.viewmodel.core)
                api(asoft.koncurrent.later.core)
                api(asoft.identifier.core)
                api(asoft.kash.core)
                api(asoft.kotlinx.collections.interoperable)
                api(kotlinx.datetime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
                implementation(asoft.koncurrent.later.coroutines)
                implementation(asoft.live.test)
                implementation(projects.presentersMock)
                implementation(asoft.koncurrent.primitives.mock)
            }
        }
    }
}