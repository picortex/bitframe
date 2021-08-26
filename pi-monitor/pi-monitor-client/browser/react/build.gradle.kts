import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
    id("com.bmuschko.docker-java-application")
}

applikation {
    debug()
    release()
}

rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
    rootProject.the<NodeJsRootExtension>().versions.webpackDevServer.version =
        "4.0.0"
}

kotlin {
    jvm { library() }

    js(IR) { browser { application() } }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-core"))
                api(project(":bitframe-client-sdk-test"))
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines)) {
                    version { strictly(vers.kotlinx.coroutines) }
                }
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(project(":bitframe-ui-react"))
                implementation(asoft("applikation-runtime", vers.asoft.builders))
            }
        }

        val jvmTest by getting {
            dependencies {
                api(project(":pi-monitor-client-test"))
            }
        }
    }
}


val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("webpackJsRelease")
    from("nginx:stable-alpine")
    destFile.set(file("build/websites/js/Dockerfile"))
    copyFile("./release", "/usr/share/nginx/html")
    exposePort(80)
    defaultCommand("nginx", "-g", "daemon off;")
}

val createDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    inputDir.set(file("build/websites/js"))
    images.add("pi-monitor-client-react:${vers.bitframe.current}")
}

val createDockerContainer by tasks.creating(DockerCreateContainer::class) {
    dependsOn(createDockerImage)
    targetImageId(createDockerImage.imageId)
    hostConfig.portBindings.set(listOf("8080:80"))
    hostConfig.autoRemove.set(true)
}

val startDockerContainer by tasks.creating(DockerStartContainer::class) {
    dependsOn(createDockerContainer)
    targetContainerId(createDockerContainer.containerId)
}

val stopDockerContainer by tasks.creating(DockerStopContainer::class) {
    targetContainerId(createDockerContainer.containerId)
}

val acceptanceTestSetup by tasks.creating {
    dependsOn("cleanAllTests")
    dependsOn(startDockerContainer)
    finalizedBy("allTests")
}

val acceptanceTestTearDown by tasks.creating {
    finalizedBy(stopDockerContainer)
}

val acceptanceTests by tasks.creating {
    dependsOn(acceptanceTestSetup)
    finalizedBy(acceptanceTestTearDown)
}