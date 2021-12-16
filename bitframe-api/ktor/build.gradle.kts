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
                api(projects.bitframeApiCore)
                api(projects.bitframeAuthenticationServiceClientKtor)
                api(ktor.client.core)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(ktor.client.cio)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":pi-monitor-test-testing"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(ktor.client.js)
            }
        }
    }
}
