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
                api(project(":cache-test"))
                api(project(":bitframe-sdk-server-core"))
                api(asoft("result-core", vers.asoft.duality))
                api(asoft("kotlinx-serialization-mapper", vers.asoft.mapper))
                api(asoft("logging-console", vers.asoft.logging))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":cache-test"))
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