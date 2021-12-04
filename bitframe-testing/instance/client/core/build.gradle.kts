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
                api(project(":bitframe-authentication-service-client-ktor"))
                api(project(":bitframe-sdk-client-ktor"))
                api(asoft.cache.mock)
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
                implementation(project(":bitframe-testing-containers"))
            }
        }

        val jsMain by getting {
            dependencies {
                api("io.ktor:ktor-client-js:${vers.ktor}")
            }
        }
    }
}
