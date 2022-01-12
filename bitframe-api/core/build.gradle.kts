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
                api(projects.bitframeCore)
                api(projects.bitframeAuthenticationServiceClientCore)
                api(asoft.platform.core)
                api(asoft.later.ktx)
                api(kotlinx.coroutines.core)
            }
        }
    }
}