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
                api(project(":pi-monitor-core"))
                api(asoft("expect-core",vers.asoft.expect))
                api(asoft("test-coroutines",vers.asoft.test))
            }
        }

        val jvmMain by getting {
            dependencies {
                api("com.codeborne:selenide:${vers.selenide}")
            }
        }
    }
}