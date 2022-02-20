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

afterEvaluate {
    file("src/commonTest/kotlin/TestConfig.kt").apply {
        if (!exists()) createNewFile()
        writeText("""val API_MODE = "${System.getenv("API_MODE") ?: "MOCK"}"${"\n"}""")
        appendText("""val API_URL = "${System.getenv("API_URL") ?: ""}"${"\n"}""")
    }
}