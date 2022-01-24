import dev.petuska.npm.publish.task.NpmPublishTask

plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
    id("org.jetbrains.dokka")
}

apply<types.PostProcessTypescriptTypesPlugin>()

kotlin {
    js(IR) {
        val main by compilations
        main.outputModuleName = "pi-monitor-client-sdk-full"
        library()
        binaries.library()
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.piMonitorApiKtor)
                api(projects.piMonitorSdkClientReact)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.core)
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
                types = "index.d.ts"
                dependencies {
                    "platform" to "1.3.6"
                    "@js-joda/core" to "4.0.0"
                    "@react-native-async-storage/async-storage" to "1.15.9"
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

afterEvaluate {
    tasks.withType(NpmPublishTask::class.java).forEach {
        it.dependsOn("purifyTypes")
    }
}