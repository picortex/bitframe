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
                api(projects.bitframeCore)
                api(projects.bitframeApiCore)
                api(projects.bitframePresenters)
                api(asoft.cache.api)
                api(asoft.platform.core)
                api(asoft.later.ktx)
                api(asoft.viewmodel.core)
                api(kotlinx.coroutines.core)
            }
        }
    }
}