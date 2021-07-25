plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { browserLib() }

    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
    }
}