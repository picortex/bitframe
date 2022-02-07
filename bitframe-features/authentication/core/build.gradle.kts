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
                api(projects.bitframeCore)
                api(projects.bitframeActors)
                api(asoft.later.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(asoft.expect.core)
            }
        }
    }
}
