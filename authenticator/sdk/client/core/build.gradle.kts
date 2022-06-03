plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {

    jvm { library() }

    js(IR) {
        library(testTimeout = 20000)
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.authenticatorApiPublicCore)
                api(projects.bitframeSdkClientCore)
                api(projects.presentersCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.authenticatorApiPublicTest)
                implementation(projects.presentersMock)
                implementation(projects.bitframeServiceBuilderSdkClientMock)
            }
        }
    }
}