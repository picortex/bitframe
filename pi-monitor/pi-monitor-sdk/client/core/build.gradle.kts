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
                api(projects.bitframeSdkClientCore)
            }
        }

        val commonTest by getting {
            dependencies {
//                implementation(projects.piMonitorTestTesting)
                implementation(projects.piMonitorApiMock)
                implementation(asoft.viewmodel.test.expect)
                implementation(asoft.expect.core)
            }
        }
    }
}