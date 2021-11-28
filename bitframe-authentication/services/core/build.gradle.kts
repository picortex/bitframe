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
                api(project(":validation"))
                api(asoft.cache.api)
                api(project(":bitframe-service-core"))
                api(project(":bitframe-events-core"))
                api(project(":bitframe-authentication-dao-core"))
                api(asoft.live.core)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":bitframe-authentication-dao-inmemory"))
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
