plugins {
    kotlin("multiplatform")
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
                api(project(":cache-api"))
                api(project(":bitframe-core"))
                api(project(":bitframe-authentication-service-client-core"))
                api(asoft("platform-core", vers.asoft.platform))
                api(asoft("later-ktx", vers.asoft.later))
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines))
            }
        }

        val jsMain by getting {
            dependencies {

            }
        }
    }
}