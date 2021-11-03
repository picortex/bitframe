plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm {
        library()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":bitframe-client-sdk-core"))
                api(project(":bitframe-authentication-service-client-test"))
                api(project(":cache-test"))
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}