plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    id("picortex-publish")
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
                api(projects.bitframeServiceBuilderApiMock)
                api(projects.eventsInmemory)
                api(asoft.cache.mock)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}
