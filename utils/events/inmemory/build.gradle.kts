plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
                api(projects.eventsCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCoroutines)
            }
        }
    }
}
