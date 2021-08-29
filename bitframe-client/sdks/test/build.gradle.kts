plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
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
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft("test-coroutines", vers.asoft.test))
                api(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}