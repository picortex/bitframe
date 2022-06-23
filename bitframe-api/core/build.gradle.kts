plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
//                api(projects.bitframeAuthenticationApiCore)
                api(kotlinx.serialization.json)
                api(asoft.cache.mock)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.bitframeApiMock)
                implementation(asoft.expect.coroutines)
            }
        }

        val nonJsMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(nonJsMain)
        }

        val jsMain by getting {
            dependencies {
                api(asoft.cache.react.native)
                api(asoft.cache.browser)
            }
        }
    }
}