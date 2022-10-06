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
                api(projects.cacheMock)

                api(asoft.logging.console)
                api(kotlinx.serialization.json)
                api(asoft.platform.core)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.bitframeApiMock)
                implementation(projects.expectCoroutines)
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
                api(projects.cacheReactNative)
                api(projects.cacheBrowser)
            }
        }
    }
}