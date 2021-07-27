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

    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
                outputFileName = "main.bundle.js"
                devServer = org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.DevServer(
                    open = false,
                    static = mutableListOf(project.file("build/processedResources/js/main").absolutePath)
                )
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-core"))
                api(project(":bitframe-client-sdk-test"))
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