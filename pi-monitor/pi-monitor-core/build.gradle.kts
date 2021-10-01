plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { browserLib() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-annotations-core"))
                api(project(":bitframe-authentication-service-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}