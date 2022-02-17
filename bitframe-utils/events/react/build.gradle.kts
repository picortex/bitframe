plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    js(IR) { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.eventsCore)
                api(asoft.reakt.core)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
