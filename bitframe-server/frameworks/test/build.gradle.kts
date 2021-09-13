plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-server-framework-core"))
                api(asoft("expect-coroutines", vers.asoft.expect))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
            }
        }
    }
}