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

// ghcr.io/OWNER/IMAGE_NAME
val remoteIp = "repo"
val createDockerComposeFile by tasks.getting(DockerComposeFileTask::class) {
    version(3.8)
    service("server") {
        image("ghcr.io/picortex/bitframe:client-browser-react-${vers.picortex.bitframe}")
        ports(9090 to 8080)
    }

    service("client") {
        image("ghcr.io/picortex/bitframe:server-${vers.picortex.bitframe}")
        ports(8080 to 80)
    }
}

//val dockerStackDeploy by tasks.getting(DockerStackDeployTask::class) {
//    username = "root"
//    password = "bitframe"
//    remote = remoteIp
//    version = vers.picortex.bitframe
//    destinationDir = "/apps/pi-monitor"
//}