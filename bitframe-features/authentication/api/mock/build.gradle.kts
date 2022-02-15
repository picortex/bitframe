plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

repositories {
    publicRepos()
    mavenCentral()
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeAuthenticationApiCore)
                api(projects.bitframeAuthenticationDaod)
                api(projects.bitframeServiceConfigApiMock)
                api(projects.bitframeEventsInmemory)
                api(asoft.cache.mock)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.piMonitorTestTesting)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
