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
                api(projects.validation)
                api(asoft.cache.api)
                api(projects.bitframeServiceConfigCore)
                api(projects.bitframeEventsCore)
                api(projects.bitframeAuthenticationDaoCore)
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
