plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-client-sdk-core"))
                api(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api("com.codeborne:selenide:${vers.selenide}")
            }
        }
    }
}