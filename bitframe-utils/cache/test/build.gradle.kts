plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":cache-api"))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
