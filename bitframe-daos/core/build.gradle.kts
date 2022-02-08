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
                api(projects.bitframeActors)
                api(kotlinx.serialization.core)
                api(kotlinx.coroutines.core)
                api(kotlinx.datetime)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeDaoMock)
                implementation(asoft.expect.core)
            }
        }
    }
}