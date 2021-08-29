import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
    id("org.jetbrains.dokka")
}

rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
    rootProject.the<NodeJsRootExtension>().versions.webpackDevServer.version = "4.0.0"
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

        val jsMain by getting {
            dependencies {
                api(asoft("live-react", vers.asoft.live))
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines)) {
                    version { strictly(vers.kotlinx.coroutines) }
                }
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
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
    }

    publications {
        val js by getting {
            organization = "picortex"
            version = "${project.version}-rc.0"
            moduleName = "pi-monitor-client-sdk-full"
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