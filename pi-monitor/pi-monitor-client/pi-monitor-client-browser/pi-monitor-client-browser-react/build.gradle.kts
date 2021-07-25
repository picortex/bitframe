plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
}

applikation {
    debug()
}

kotlin {
    jvm {
        library()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { browserApp() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-core"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(project(":bitframe-ui-react"))
            }
        }

        val jvmTest by getting {
            dependencies {
                api(project(":pi-monitor-client-test-dsl"))
            }
        }
    }
}