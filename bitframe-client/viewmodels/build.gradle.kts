plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    `picortex-publish`
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft.cache.api)
                api(project(":bitframe-sdk-client-core"))
                api(project(":bitframe-presenters"))
                api(asoft.viewmodel.core)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.cache.mock)
                api(project(":bitframe-sdk-client-core"))
                api(project(":bitframe-events-inmemory"))
                api(project(":bitframe-authentication-service-client-test"))
                api(asoft.viewmodel.test.expect)
            }
        }

        val jsMain by getting {
            dependencies {
                api(project(":bitframe-events-react"))
            }
        }
    }
}