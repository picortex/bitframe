plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

val tmp = 1

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.authenticatorCore)
                api(projects.bitframeApiCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
                implementation(projects.authenticatorApiMock)
//                implementation(projects.authenticatorApiPublicTest)
                implementation(asoft.identifier.generators)
            }
        }
    }
}