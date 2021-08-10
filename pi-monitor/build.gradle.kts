import docker.DockerComposeFileTask
import docker.DockerStackDeployTask

plugins {
    `docker-compose`
}

val runWeb by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:runJsDebug")
}

val runServer by tasks.creating {
    dependsOn(":pi-monitor-server:runDebug")
}

val acceptanceTestSetup by tasks.creating {
    dependsOn(":pi-monitor-server:acceptanceTestSetup")
    finalizedBy(":pi-monitor-client-browser-react:acceptanceTestSetup")
}

val acceptanceTestTearDown by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:acceptanceTestTearDown")
    finalizedBy(":pi-monitor-server:acceptanceTestTearDown")
}

val acceptanceTest by tasks.creating {
    dependsOn(acceptanceTestSetup)
    finalizedBy(acceptanceTestTearDown)
}

val remoteIp = "65.21.254.230"
val createDockerComposeFile by tasks.getting(DockerComposeFileTask::class) {
    version(3.8)
    val registry = "$remoteIp:5000"
    service("server") {
        val name = "pi-monitor-server"
        image("$registry/$name:${vers.picortex.bitframe}")
        build(context = file("pi-monitor-server/build/binaries"))
        ports(9090 to 8080)
        container("$name-${vers.picortex.bitframe}")
    }

    service("client") {
        val name = "pi-monitor-client-browser-react"
        image("$registry/$name:${vers.picortex.bitframe}")
        build(context = file("pi-monitor-client/pi-monitor-client-browser/pi-monitor-client-browser-react/build/websites/js"))
        ports(8080 to 80)
        container("$name-${vers.picortex.bitframe}")
    }
}

val dockerStackDeploy by tasks.getting(DockerStackDeployTask::class) {
    username = "root"
    password = "bitframe"
    remote = remoteIp
    version = vers.picortex.bitframe
    destinationDir = "/apps/pi-monitor"
}