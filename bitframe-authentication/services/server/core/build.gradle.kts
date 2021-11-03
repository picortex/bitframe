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
                api(project(":bitframe-authentication-dao-core"))
                api(project(":bitframe-authentication-service-core"))
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
