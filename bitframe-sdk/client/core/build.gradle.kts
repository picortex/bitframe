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
                api(asoft.cache.api)
                api(project(":bitframe-core"))
                api(project(":bitframe-authentication-service-client-core"))
                api(asoft.platform.core)
                api(asoft.later.ktx)
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines))
            }
        }
    }
}