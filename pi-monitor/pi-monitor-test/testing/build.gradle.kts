plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }
    js(IR) { browser() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-service-ktor"))
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }

        val jvmMain by getting {
            dependencies {
                api("org.junit.jupiter:junit-jupiter-params:5.7.0")
                api("org.testcontainers:testcontainers:${vers.testContainers}")
                api("org.testcontainers:junit-jupiter:${vers.testContainers}")
//                api(project(":pi-monitor-test-containers"))
            }
        }
    }
}