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
        main.outputModuleName = "pimonitor-sdk-react"
        library()
        binaries.library()
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.pimonitorSdkClientCore)
                api(projects.bitframeSdkClientReact)
            }
        }

        val test by getting {
            dependencies {
                implementation(projects.pimonitorApiPublicTest)
                implementation(asoft.expect.coroutines)
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
            authToken = "andylamax"
        }

        repository("piCortex") {
            registry = uri("http://${vars.dev.server.ip}:1040")
            authToken = "andylamax"
        }
    }

    publications {
        val js by getting {
            organization = "picortex"
            version = "${project.version}"
            moduleName = "monitor-react"
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
    tasks.withType(dev.petuska.npm.publish.task.NpmPublishTask::class.java).forEach {
        it.dependsOn("purifyTypes")
    }
}