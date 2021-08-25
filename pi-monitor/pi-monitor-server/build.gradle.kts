import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile

plugins {
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
    id("tz.co.asoft.applikation")
}

application {
    mainClass.set("pimonitor.MainKt")
}

applikation {
    debug()
    release()
}

kotlin {
    target {
        application()
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":bitframe-server-framework-ktor"))
                api(project(":bitframe-server-dao-inmemory"))

                api(project(":pi-monitor-core"))
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("installDistRelease")
    from("openjdk:8-jre")
    runCommand("mkdir /app")
    destFile.set(file("build/binaries/Dockerfile"))
    copyFile("./release", "/app")
    workingDir("/app")
    exposePort(8080)
    defaultCommand("./bin/pi-monitor-server")
}

val createDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    inputDir.set(file("build/binaries"))
    images.add("pi-monitor-server:${vers.bitframe.current}")
}

val createDockerContainer by tasks.creating(DockerCreateContainer::class) {
    dependsOn(createDockerImage)
    targetImageId(createDockerImage.imageId)
    hostConfig.portBindings.set(listOf("9090:8080"))
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
    dependsOn("clean")
    dependsOn(startDockerContainer)
    finalizedBy("test")
}

val acceptanceTestTearDown by tasks.creating {
    finalizedBy(stopDockerContainer)
}

val acceptanceTests by tasks.creating {
    dependsOn(acceptanceTestSetup)
    finalizedBy(acceptanceTestTearDown)
}