plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

applikation {
    debug()
}

kotlin {
    js(IR) { browserApp() }

    sourceSets {
        val main by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-pre.206-kotlin-1.5.10")
            }
        }
    }
}