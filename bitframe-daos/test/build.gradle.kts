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
                api(projects.bitframeDaoCore)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeAnnotationsCore)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}