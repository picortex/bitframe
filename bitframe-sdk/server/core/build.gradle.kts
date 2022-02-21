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
                api(projects.bitframeServiceConfigCore)
                api(projects.bitframeAuthenticationSdkServerCore)
                api(asoft.platform.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeDaoMock)
                implementation(asoft.expect.core)
            }
        }
    }
}
