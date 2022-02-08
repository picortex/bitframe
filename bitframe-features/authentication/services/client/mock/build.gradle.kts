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
                implementation(projects.bitframeAuthenticationServiceClientCore)
                implementation(projects.bitframeAuthenticationServiceDaod)
                api(projects.bitframeEventsInmemory)
                api(asoft.cache.mock)
            }
        }

        val commonTest by getting {
            dependencies {
                api(projects.piMonitorTestTesting)
                api(asoft.expect.coroutines)
            }
        }
    }
}
