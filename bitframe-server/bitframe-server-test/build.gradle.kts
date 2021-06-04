plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

kotlin {
    jvm {
        library("15")
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-server-core"))
//                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}