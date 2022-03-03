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
                api(asoft.expect.coroutines)
                api(projects.pimonitorApiPublicCore)
            }
        }
    }
}

afterEvaluate {
    file("src/commonMain/kotlin/pimonitor/client/TestConfig.kt").apply {
        if (!exists()) createNewFile()
        writeText("package pimonitor.client\n\n")
        appendText("""val API_MODE = "${System.getenv("API_MODE") ?: "MOCK"}"${"\n"}""")
        appendText("""val API_URL  = "${System.getenv("API_URL") ?: ""}"${"\n"}""")
    }
}