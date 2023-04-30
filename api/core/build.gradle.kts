plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
//    id("picortex-publish")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.keepMock)

                api(projects.lexiConsole)
                api(kotlinx.serialization.json)
                api(projects.habitatCore)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.bitframeApiMock)
                implementation(projects.kommanderCoroutines)
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
//                api(projects.keepReactNative)
//                api(projects.keepBrowser)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A kotlin multiplatform library"
)