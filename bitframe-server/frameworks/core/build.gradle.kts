plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-core"))
                implementation(project(":bitframe-authentication-service-core"))
                api(project(":bitframe-authentication-service-server-core"))
                api(project(":bitframe-events-inmemory"))
                api(asoft.cache.mock)
                api(project(":bitframe-sdk-server-core"))
                api(asoft.kotlinx.serialization.mapper)
                api(asoft.logging.console)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.cache.mock)
                implementation(project(":bitframe-server-framework-test"))
                implementation(project(":bitframe-server-dao-inmemory"))
                implementation(project(":bitframe-events-inmemory"))
                implementation(kotlinx("serialization-core", vers.kotlinx.serialization))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
            }
        }
    }
}