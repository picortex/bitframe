plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
//    id("picortex-publish")
}

kotlin {
    js(IR) { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeServiceBuilderSdkClientCore)
                api(asoft.live.react)
            }
        }
    }
}