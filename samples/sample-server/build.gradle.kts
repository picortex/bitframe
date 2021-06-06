plugins {
    kotlin("jvm")
    id("tz.co.asoft.applikation")
}

applikation {
    debug()
}

application {
    mainClass.set("com.picortex.bitframe.MainKt")
}

kotlin {
    target { application() }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(project(":bitframe-server-ktor"))
            }
        }
    }
}