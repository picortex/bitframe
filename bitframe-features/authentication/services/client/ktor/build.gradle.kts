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
                api(projects.bitframeAuthenticationServiceClientCore)
                api(projects.bitframeServiceConfigClientKtor)
                api(projects.response)
                api(kotlinx.serialization.json)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.cache.mock)
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
