plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

kotlin {
    jvm {
        library()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-client-sdk-core"))
                api("io.ktor:ktor-client-core:${vers.ktor}")
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
                api(asoft("expect-core", vers.asoft.expect))
            }
        }

        val jvmMain by getting {
            dependencies {
                api("io.ktor:ktor-client-cio:${vers.ktor}")
            }
        }

        val jsMain by getting {
            dependencies {
                api("io.ktor:ktor-client-js:${vers.ktor}")
            }
        }
    }
}