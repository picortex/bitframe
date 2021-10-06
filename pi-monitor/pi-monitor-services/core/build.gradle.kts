plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-core"))
                api(project(":bitframe-client-sdk-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}