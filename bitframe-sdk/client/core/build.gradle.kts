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
                api(asoft.viewmodel.coroutines)
                api(kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.bitframeAuthenticationServiceClientMock)
                implementation(projects.bitframeApiMock)
                implementation(asoft.expect.coroutines)
                implementation(asoft.viewmodel.test.expect)
            }
        }
    }
}