import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
    id("tz.co.asoft.applikation")
}

application {
    mainClass.set("bitframe.server.testing.MainKt")
}

applikation {
    debug()
    release()
}

kotlin {
    target {
        application()
        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "16"
        }
        compilations.all {
            kotlinOptions.jvmTarget = "16"
        }
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.bitframeServerFrameworkKtor)
            }
        }

        val test by getting {
            dependencies {
                implementation(projects.bitframeServerFrameworkTest)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("installDistRelease")
    dependsOn(":bitframe-testing-instance-client-browser:jsBrowserProductionWebpack")
    dependsOn(":bitframe-testing-instance-client-browser:webpackJsRelease")
    from("openjdk:16-jdk")
    runCommand("mkdir /app /app/public")
    destFile.set(file("build/binaries/Dockerfile"))
    copyFile("./release", "/app")
    copyFile("./public", "/app/public")
    workingDir("/app")
    exposePort(8080)
    defaultCommand("./bin/bitframe-testing-instance-server", "/app/public")
    doLast {
        copy {
            from(rootProject.file("bitframe-testing/instance/client/browser/build/websites/js/release"))
            into(file("build/binaries/public"))
            exclude("*.ts", "*.map")
        }
    }
}

val createDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    inputDir.set(file("build/binaries"))
    images.addAll("bitframe:${vers.bitframe.current}")
}

fun dockerPushTo(remote: String) = tasks.creating(Exec::class) {
    dependsOn(createDockerImage)
    val localTag = "bitframe:${vers.bitframe.current}"
    val remoteName = "$remote/bitframe:${vers.bitframe.current}"
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

val run by tasks.getting(JavaExec::class) {
    val public = properties.getOrDefault("public", "/default")
    args = listOf(public.toString())
}