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
                api(projects.bitframeDaoMock)
                api(projects.bitframeDaoMongo)
                api(projects.bitframeDaoFile)
                api(squareup.okio.core)
                api(kotlinx.serialization.toml.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(squareup.okio.fake)
                implementation(projects.expectCore)
            }
        }
    }
}