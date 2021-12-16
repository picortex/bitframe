plugins {
    kotlin("multiplatform")
}

repositories {
    publicRepos()
}

kotlin {
    jvm { library() }

    js(IR) { library() }

    sourceSets {
        val commonTest by getting {
            dependencies {
                api(projects.bitframeAuthenticationServiceClientCore)
                api(projects.bitframeSdkClientCore)
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
                implementation(projects.bitframeTestingContainers)
            }
        }

        val jsMain by getting {
            dependencies {
                api(ktor.client.js)
            }
        }
    }
}
