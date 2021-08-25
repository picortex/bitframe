plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { browserLib() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-core"))
                api(asoft("email-core", vers.asoft.contacts))
            }
        }
    }
}