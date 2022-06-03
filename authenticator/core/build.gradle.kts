plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

var tmp = 4
kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.pimonitorCommonCore)
                api(projects.bitframeAuthenticationCore)
                api(projects.presentersCore)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
                api(projects.response)
            }
        }
    }
}