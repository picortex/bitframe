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
                api(projects.bitframeAuthenticationServiceCore)
                api(projects.bitframeCore)
                api(projects.bitframeServiceConfigClientCore)
                api(asoft.later.ktx)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
            }
        }
    }
}
