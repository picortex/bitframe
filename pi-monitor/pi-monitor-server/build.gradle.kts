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
                implementation(project(":bitframe-server-framework-test"))
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("installDistRelease")
    dependsOn(":pi-monitor-client-browser-react:webpackJsDebug")
    from("openjdk:8-jre")
    runCommand("mkdir /app /app/public")
    destFile.set(file("build/binaries/Dockerfile"))
    copyFile("./release", "/app")
    copyFile("./public", "/app/public")
    workingDir("/app")
    exposePort(8080)
    defaultCommand("./bin/pi-monitor-server", "/app/public")
    doLast {
        copy {
            from(rootProject.file("pi-monitor/pi-monitor-client/browser/react/build/websites/js/debug"))
            into(file("build/binaries/public"))
            exclude("main.bundle.js.*")
        }
    }
}

val createDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    inputDir.set(file("build/binaries"))
    images.addAll("pi-monitor:${vers.bitframe.current}")
}

fun dockerPushTo(remote: String) = tasks.creating(Exec::class) {
    dependsOn(createDockerImage)
    val localTag = "pi-monitor:${vers.bitframe.current}"
    val remoteName = "$remote/pi-monitor:${vers.bitframe.current}"
    commandLine("docker", "tag", localTag, remoteName)
    doLast {
        exec { commandLine("docker", "push", remoteName) }
    }
}

val dockerPushToAndylamax by dockerPushTo("localhost:1030")

val dockerPushToPiCortex by dockerPushTo("${vars.dev.server.ip}:1030")

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

val run by tasks.getting(JavaExec::class) {
    val public = properties.getOrDefault("public", "/default")
    args = listOf(public.toString())
}