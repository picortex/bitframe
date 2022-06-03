plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
                api(projects.authenticatorApiCore)
                api(projects.pimonitorCommonCore)
                api(projects.bitframeApiCore)
                api(projects.bitframeAuthenticationCore)
                api(projects.presentersCore)
                api(projects.akkountsCore)
                api(asoft.kotlinx.serialization.mapper)
                api(ktor.client.core)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
                api(projects.response)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(ktor.client.cio)
            }
        }
    }
}