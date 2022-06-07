plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("picortex-publish")
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
                api(projects.bitframeActorCore)
                api(asoft.kotlinx.serialization.mapper)
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