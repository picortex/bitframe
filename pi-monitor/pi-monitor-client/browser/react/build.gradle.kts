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
    debug(
        "url" to "http://localhost:8081"
    )
    release()
}

repositories {
    publicRepos()
}

rootProject.plugins.withType<NodeJsRootPlugin> {
    rootProject.the<NodeJsRootExtension>().versions.apply {
        webpackDevServer.version = "4.1.0"
        webpackCli.version = "4.9.0"
    }
}

kotlin {
    jvm { library() }

    js(IR) {
        browser { application() }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":pi-monitor-client-sdk-full"))
                api(kotlinx("coroutines-core", vers.kotlinx.coroutines))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(project(":bitframe-ui-react"))
                implementation(asoft.applikation.runtime)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(project(":pi-monitor-client-test"))
                implementation(project(":pi-monitor-test-testing"))
            }
        }
    }
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("compileProductionExecutableKotlinJs", "webpackJsRelease")
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

val jvmTest by tasks.getting(Test::class) {
    val props = mutableMapOf<String, Any>(
        "selenide.headless" to (System.getenv("HEADLESS") == "true")
    )
    if (System.getenv("TEST_MODE") == "CI") {
        dependsOn(":pi-monitor-server:createDockerfile")
    }
    systemProperties(props)
    testLogging {
        showStandardStreams = true
    }
}