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
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api(asoft("live-core", vers.asoft.live))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
