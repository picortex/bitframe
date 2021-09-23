import testing.testing

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

repositories {
    publicRepos()
    mavenCentral()
}

kotlin {
    jvm { library() }

    js(IR) {
        browser {
            testing("integration")
        }
        library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-client-sdk-core"))
                api("io.ktor:ktor-client-core:${vers.ktor}")
                api(asoft("result-core", vers.asoft.duality))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("kotlinx-coroutines-test", vers.asoft.foundation))
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }

        val jvmMain by getting {
            dependencies {
                api("io.ktor:ktor-client-cio:${vers.ktor}")
            }
        }

//        val jvmTest by getting {
//            dependencies {
//                implementation(asoft("expect-coroutines", vers.asoft.expect))
//            }
//        }

        val jsMain by getting {
            dependencies {
                api("io.ktor:ktor-client-js:${vers.ktor}")
            }
        }
    }
}
