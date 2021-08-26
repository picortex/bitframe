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
                api(project(":bitframe-core"))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlinx("coroutines-core-js", vers.kotlinx.coroutines))
            }
        }
    }
}