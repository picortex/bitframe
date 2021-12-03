plugins {
//    id("com.google.devtools.ksp")
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
                api(project(":bitframe-annotations-core"))
                api(kotlinx("serialization-core", vers.kotlinx.serialization))
                api("io.ktor:ktor-http:${vers.ktor}")
                api(asoft.kotlinx.serialization.mapper)
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
