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
    target {
        application()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(project(":bitframe-server-ktor"))
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
                implementation(project(":bitframe-server-test"))
            }
        }
    }
}