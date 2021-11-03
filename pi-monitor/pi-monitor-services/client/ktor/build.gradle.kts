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
                api(project(":pi-monitor-service-client-core"))
                api(project(":bitframe-service-client-ktor"))
                api(project(":bitframe-core"))
                api(project(":bitframe-authentication-service-client-ktor"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}