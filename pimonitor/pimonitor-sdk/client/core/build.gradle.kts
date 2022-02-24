plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }

    js(IR) {
        library(testTimeout = 20000)
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.pimonitorApiPublicCore)
                api(projects.bitframeSdkClientCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.pimonitorApiPublicTest)
                implementation(asoft.viewmodel.test.expect)
                implementation(asoft.expect.core)
            }
        }
    }
}