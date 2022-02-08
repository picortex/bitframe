import testing.testing

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
    jvm {
        library()
        testing("unit", "integration")
    }

    js(IR) {
        library()
        testing("unit", "integration")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeAuthenticationServiceClientCore)
                api(projects.bitframeAuthenticationServiceDaod)
                api(projects.bitframeServiceConfigClientMock)
                api(projects.bitframeEventsInmemory)
                api(asoft.cache.mock)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.piMonitorTestTesting)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
