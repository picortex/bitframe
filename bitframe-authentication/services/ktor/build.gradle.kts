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
                api(project(":bitframe-authentication-service-core"))
                api(project(":bitframe-core"))
                api(project(":bitframe-service-ktor"))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
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
