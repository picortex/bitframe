plugins {
    id("dev.petuska.npm.publish")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    `picortex-publish`
}

apply<types.PostProcessTypescriptTypesPlugin>()

kotlin {
    jvm { library() }
    js(IR) {
        val main by compilations
        main.outputModuleName = "pimonitor-api"
        library()
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.pimonitorApiKtor)
                api(projects.pimonitorApiMock)
            }
        }

        val commonTest by getting {
            dependencies {
                api(asoft.expect.coroutines)
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
            moduleName = "monitor-api"
            readme = file("ReadMe.md")

            packageJson {
                types = "index.d.ts"

                dependencies {
                    "platform" to "1.3.6"
                    "@js-joda/core" to "4.0.0"
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