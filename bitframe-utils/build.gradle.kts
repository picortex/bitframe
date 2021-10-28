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
            }
        }

        val commonTest by getting {
            dependencies {
                api(kotlinx("serialization-json", vers.kotlinx.serialization))
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}
