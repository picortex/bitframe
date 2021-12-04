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
                api(asoft.cache.mock)
                api(project(":bitframe-testing-containers"))
                api(project(":bitframe-testing-sdk-browser"))
                api(project(":pi-monitor-client-sdk-core"))
                api(project(":bitframe-service-client-ktor"))
                api(asoft.expect.coroutines)
            }
        }

        val jsMain by getting {
            dependencies {
//                api(npm("@react-native-async-storage/async-storage", vers.npm.asyncStorage)) {
//                    because("Removing this dependency fails IDEA sync")
//                }
            }
        }
    }
}