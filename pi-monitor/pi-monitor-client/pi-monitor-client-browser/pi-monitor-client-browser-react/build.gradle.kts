import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile

plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
    id("com.bmuschko.docker-java-application")
}

applikation {
    debug()
    release()
}

kotlin {
    jvm { library() }

    js(IR) {
        browser {
            application()
        }

        sourceSets {
            val commonMain by getting {
                dependencies {
                    api(project(":pi-monitor-core"))
                    api(project(":bitframe-client-sdk-test"))
                }
            }

            val jsMain by getting {
                dependencies {
                    implementation(project(":bitframe-ui-react"))
                }
            }

            val jvmTest by getting {
                dependencies {
                    api(project(":pi-monitor-client-test-dsl"))
                }
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
    images.add("pi-monitor-client-react:${vers.picortex.bitframe}")
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

val preTest by tasks.creating {
    dependsOn("cleanAllTests")
    dependsOn(startDockerContainer)
    finalizedBy("allTests")
}

val acceptanceTests by tasks.creating {
    dependsOn(preTest)
    finalizedBy(stopDockerContainer)
}