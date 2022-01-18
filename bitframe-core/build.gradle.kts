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
                api(projects.bitframeAnnotationsCore)
                api(kotlinx.serialization.core)
                api(ktor.http)
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
