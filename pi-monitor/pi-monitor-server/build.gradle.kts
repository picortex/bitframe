plugins {
    kotlin("jvm")
    id("tz.co.asoft.applikation")
}

application {
    mainClass.set("pimonitor.MainKt")
}

applikation {
    debug()
    release()
}

kotlin {
    target { application() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-server-ktor"))
            }
        }
    }
}