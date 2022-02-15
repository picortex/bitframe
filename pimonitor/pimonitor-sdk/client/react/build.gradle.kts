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
                api(projects.pimonitorSdkClientCore)
                api(projects.bitframeSdkClientReact)
            }
        }
    }
}