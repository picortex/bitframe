plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library(); }
    js { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
//                api(kotlinx("serialization-core", vers.kotlinx.serialization))
            }
        }
    }
}