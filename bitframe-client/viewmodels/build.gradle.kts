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
                api(project(":bitframe-client-sdk-core"))
                api(project(":bitframe-presenters"))
                api(asoft("viewmodel-core", vers.asoft.viewmodel))
            }
        }

        val commonTest by getting {
            dependencies {
                api(project(":bitframe-client-sdk-test"))
                api(asoft("viewmodel-test-expect", vers.asoft.viewmodel))
            }
        }
    }
}