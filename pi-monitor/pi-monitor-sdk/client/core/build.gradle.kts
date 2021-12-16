plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }

    js(IR) {
        library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.piMonitorApiCore)
                api(project(":bitframe-client-viewmodels"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":pi-monitor-test-testing"))
                implementation(asoft.viewmodel.test.expect)
                implementation(asoft.expect.core)
            }
        }

        val jsMain by getting {
            dependencies {
                api(asoft.viewmodel.react)
            }
        }
    }
}