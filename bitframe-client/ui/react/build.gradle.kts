plugins {
    kotlin("js")
    id("tz.co.asoft.library")
}

repositories {
    publicRepos()
}

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeSdkClientReact)
                api(asoft.viewmodel.react)
                api(asoft.reakt.web)
            }
        }
    }
}