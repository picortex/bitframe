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
                api(asoft.viewmodel.core)
                api(project(":bitframe-annotations-core"))
                api(asoft.kotlinx.collections.interoperable)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}