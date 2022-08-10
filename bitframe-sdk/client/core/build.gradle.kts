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
                api(projects.bitframeApiCore)
                api(projects.bitframeServiceBuilderSdkClientCore)
//                api(projects.bitframeAuthenticationSdkClientCore)
            }
        }

        val nonJsMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(nonJsMain)
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeServiceBuilderSdkClientCore)
                implementation(asoft.expect.coroutines)
                implementation(asoft.live.test)
            }
        }
    }
}