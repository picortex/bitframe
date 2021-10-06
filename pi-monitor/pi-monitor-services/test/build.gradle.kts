plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-service-ktor"))
                api(project(":pi-monitor-service-stub"))
                api(project(":pi-monitor-test-testing"))
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}