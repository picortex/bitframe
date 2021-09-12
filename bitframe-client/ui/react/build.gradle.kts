plugins {
    kotlin("js")
    id("tz.co.asoft.library")
}

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-client-viewmodels"))
                api(asoft("viewmodel-react", vers.asoft.viewmodel))
                api(asoft("reakt-web", vers.asoft.reakt))
            }
        }
    }
}