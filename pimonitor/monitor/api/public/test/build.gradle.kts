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
                api(projects.pimonitorMonitorApiPublicCore)
            }
        }
    }
}

afterEvaluate {
    val content = buildString {
        appendLine("package pimonitor.client\n\n")
        appendLine("""val API_MODE = "${System.getenv("API_MODE") ?: "MOCK"}"${"\n"}""")
        appendLine("""val API_URL  = "${System.getenv("API_URL") ?: ""}"${"\n"}""")
    }
    println("Running with\n$content")
    file("src/commonMain/kotlin/pimonitor/client/TestConfig.kt").apply {
        if (!exists()) createNewFile()
        writeText(content)
    }
}