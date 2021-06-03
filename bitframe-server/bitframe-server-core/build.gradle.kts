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
                api("io.ktor:ktor-http:${vers.ktor}")
            }
        }

        val commonTest by getting {
            dependencies {
                api(project(":bitframe-server-test"))
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}