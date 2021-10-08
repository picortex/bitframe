plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

kotlin {
    jvm { library() }

    js(IR) {
        val main by compilations
        main.outputModuleName = "pi-monitor-client-sdk-full"
        browserLib()
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-client-sdk-core"))
                api(project(":bitframe-client-viewmodels"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":pi-monitor-test-testing"))
                implementation(asoft("viewmodel-test-expect", vers.asoft.viewmodel))
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }

        val jsMain by getting {
            dependencies {
                api(asoft("viewmodel-react", vers.asoft.viewmodel))
            }
        }
    }
}

npmPublishing {
    repositories {
        repository("github") {
            registry = uri("https://npm.pkg.github.com/") // DO NOT REMOVE THE TRAILING SLASH
            authToken = System.getenv("GH_TOKEN")
        }

        repository("andylamax") {
            registry = uri("http://localhost:1040")
            authToken = ""
        }

        repository("piCortex") {
            registry = uri("http://${vars.dev.server.ip}:1040")
            authToken = ""
        }
    }

    publications {
        val js by getting {
            organization = "picortex"
            version = "${project.version}"
            moduleName = "pi-monitor-client-full"
            readme = file("README.md")
            packageJson {
                dependencies {
                    "platform" to "1.3.6"
                    "@js-joda/core" to "4.0.0"
                }

                peerDependencies {
                    "react" to "*"
                }

                repository {
                    type = "git"
                    url = "https://github.com/picortex/monitor-client.git"
                }
            }
        }
    }
}