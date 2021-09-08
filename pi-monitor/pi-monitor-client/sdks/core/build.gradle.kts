plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
    id("org.jetbrains.dokka")
}

kotlin {
    jvm { library() }

    js(IR) {
        val main by compilations
        main.outputModuleName = "pi-monitor-client-sdk-core"
        browserLib()
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft("logging-console", vers.asoft.logging))
                api(project(":pi-monitor-core"))
                api(project(":bitframe-client-sdk-test"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
            }
        }
    }
}

configurePublishing {
    repositories {
        maven {
            name = "andylamax"
            url = uri("http://localhost:1050/repository/internal/")
            isAllowInsecureProtocol = true
            credentials {
                username = "admin"
                password = "admin@123"
            }
        }

        maven {
            name = "piCortex"
            url = uri("http://${vars.dev.server.ip}:1050/repository/internal/")
            isAllowInsecureProtocol = true
            credentials {
                username = "admin"
                password = "admin@123"
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
            moduleName = "pi-monitor-client-core"
            readme = file("README.md")
            packageJson {
                repository {
                    type = "git"
                    url = "https://github.com/picortex/monitor-client.git"
                }
            }
        }
    }
}