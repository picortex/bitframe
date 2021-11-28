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
                api(asoft.cache.mock)
                api(project(":bitframe-service-client-ktor"))
                api(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api("org.junit.jupiter:junit-jupiter-params:5.7.0")
                api("org.testcontainers:testcontainers:${vers.testContainers}")
                api("org.testcontainers:junit-jupiter:${vers.testContainers}")
            }
        }
    }
}