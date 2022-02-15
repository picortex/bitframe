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
                api(projects.pimonitorApiPublic)
                api(projects.bitframeSdkClientCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.pimonitorApiMock)
                implementation(asoft.viewmodel.test.expect)
                implementation(asoft.expect.core)
            }
        }
    }
}