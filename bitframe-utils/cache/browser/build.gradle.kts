plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    //`picortex-publish` TODO: figure out how to publish js only artifacts as well, not just multiplatform
}

kotlin {
    js(IR) { library() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":cache-api"))
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
