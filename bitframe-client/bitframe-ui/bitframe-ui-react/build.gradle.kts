plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(asoft("reakt-buttons", vers.asoft.reakt))
                api(asoft("reakt-text", vers.asoft.reakt))
                api(asoft("reakt-layouts", vers.asoft.reakt))
                api(asoft("reakt-navigation", vers.asoft.reakt))
                api(asoft("reakt-form", vers.asoft.reakt))
            }
        }
    }
}