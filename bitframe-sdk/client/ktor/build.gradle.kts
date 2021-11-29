plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

repositories {
    publicRepos()
    mavenCentral()
}

kotlin {
    jvm { library() }

    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-sdk-client-core"))
                api(project(":bitframe-authentication-service-client-ktor"))
                api("io.ktor:ktor-client-core:${vers.ktor}")
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api("io.ktor:ktor-client-cio:${vers.ktor}")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":pi-monitor-test-testing"))
            }
        }

        val jsMain by getting {
            dependencies {
                api("io.ktor:ktor-client-js:${vers.ktor}")
            }
        }
    }
}
