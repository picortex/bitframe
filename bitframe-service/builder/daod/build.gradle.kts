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
                api(projects.mailerMock)
                api(projects.bitframeServiceBuilderCore)
                api(projects.bitframeDaoCore)
                api(kotlinx.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }

        val jvmMain by getting {
            dependencies {
                api(projects.mailerSmtp)
            }
        }
    }
}