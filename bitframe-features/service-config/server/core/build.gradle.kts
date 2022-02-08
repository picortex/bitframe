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
                api(asoft.cache.api)
                api(projects.bitframeServiceConfigCore)
                api(projects.bitframeDaoCore)
                api(asoft.platform.core)
                api(projects.bitframeServiceConfigDaod)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}
