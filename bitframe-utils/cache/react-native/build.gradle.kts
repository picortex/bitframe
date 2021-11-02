plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    //`picortex-publish` TODO: figure out how to publish js only artifacts as well, not just multiplatform
}

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":cache-api"))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
                api(asoft("later-core", vers.asoft.later))
                api(npm("@react-native-async-storage/async-storage", "1.15.9"))
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
