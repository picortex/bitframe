plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeAuthenticationApiCore)
                api(projects.bitframeServiceConfigSdkClientCore)
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
                implementation(projects.bitframeAuthenticationApiMock)
                implementation(asoft.expect.coroutines)
                implementation(asoft.viewmodel.test.expect)
            }
        }
    }
}