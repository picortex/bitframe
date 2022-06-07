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
                api(projects.bitframeAuthenticationApiCore)
                api(projects.bitframeServiceBuilderSdkClientCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeAuthenticationApiMock)
                implementation(projects.bitframeServiceBuilderSdkClientMock)
                implementation(asoft.expect.coroutines)
                implementation(asoft.viewmodel.test.expect)
            }
        }
    }
}