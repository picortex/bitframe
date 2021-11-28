plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-server-framework-core"))
                api(asoft.expect.coroutines)
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
            }
        }
    }
}