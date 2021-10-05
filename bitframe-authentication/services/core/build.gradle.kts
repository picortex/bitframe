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
                api(project(":bitframe-service-core"))
                api(project(":bitframe-authentication-dao-core"))
                api(asoft("live-core", vers.asoft.live))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":bitframe-authentication-dao-inmemory"))
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}
