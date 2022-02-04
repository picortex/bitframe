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
                api(projects.bitframeAuthenticationDaoCore)
                api(projects.bitframeServiceConfigServerCore)
                implementation(projects.bitframeAuthenticationServiceCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeAuthenticationDaoInmemory)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
