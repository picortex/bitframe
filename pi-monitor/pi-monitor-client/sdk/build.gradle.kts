import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("dev.petuska.npm.publish")
}

rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
    rootProject.the<NodeJsRootExtension>().versions.webpackDevServer.version = "4.0.0"
}

kotlin {
    jvm { library() }

    js(IR) {
        val main by compilations
        main.outputModuleName = "pi-monitor-client-sdk"
        browserLib()
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-core"))
                api(project(":bitframe-client-sdk-test"))
                api(project(":bitframe-client-viewmodels"))
            }
        }
    }
}

npmPublishing {
    repositories {
        repository("github") {
            registry = uri("https://npm.pkg.github.com")
            authToken = System.getenv("GH_TOKEN")
        }
    }

    publications {
        val js by getting {
            scope = "picortex"
            moduleName = "pi-monitor-client-sdk"
            files { from("src/jsMain/npm") }
            packageJson {
                repository {
                    type = "git"
                    url = "https://github.com/picortex/monitor-client.git"
                }
            }
        }
    }
}