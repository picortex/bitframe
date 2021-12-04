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
                api(project(":bitframe-authentication-service-client-core"))
                api(project(":bitframe-core"))
                api(project(":bitframe-service-client-ktor"))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
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
