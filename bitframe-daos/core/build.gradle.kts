plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    jvm {
        library()
        withJava()
    }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api(asoft.kotlinx.serialization.mapper)
                api(kotlinx.coroutines.core)
                api(asoft.later.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}