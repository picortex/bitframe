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
                api(projects.bitframeTestingContainers)
                api(projects.bitframeTestingSdkBrowser)
                api(projects.bitframeServiceClientKtor)
                api(asoft.expect.coroutines)
                api(projects.piMonitorApiKtor)
                api(projects.piMonitorSdkClientCore)
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