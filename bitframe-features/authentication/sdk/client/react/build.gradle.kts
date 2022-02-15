plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    js(IR) { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeAuthenticationSdkClientCore)
                api(projects.bitframeServiceConfigSdkClientReact)
                api(projects.bitframeEventsReact)
                api(asoft.viewmodel.react)
            }
        }
    }
}