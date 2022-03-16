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

    val tmp = 3
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.bitframeAuthenticationCore)
                api(projects.bitframeServiceBuilderApiCore)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                api(projects.bitframeAuthenticationApiMock)
                api(asoft.expect.coroutines)
            }
        }
    }
}
