import docker.DockerComposeFileTask

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

fun DockerComposeFileTask.configure(serverPort: Int, clientPort: Int) {
    version(3.8)
    service("server") {
        image("ghcr.io/picortex/bitframe:server-${vers.bitframe.current}")
        ports(serverPort to 8080)
    }

    service("client") {
        image("ghcr.io/picortex/bitframe:client-browser-react-${vers.bitframe.current}")
        ports(clientPort to 80)
    }
}

val setVersions by tasks.creating {
    fun writeVersion(name: String, value: String) {
        val dir = file("build/versioning").apply { mkdirs() }
        val file = File(dir, "$name.txt").apply { if (!exists()) createNewFile() }
        val safe = File(dir, "${name}_safe.txt").apply { if (!exists()) createNewFile() }
        file.writeText(value)
        safe.writeText(value.replace(".", "_"))
    }
    doLast {
        writeVersion("current", vers.bitframe.current)
        writeVersion("previous", vers.bitframe.previous)
    }
}

val createDockerComposeStagingFile by tasks.creating(DockerComposeFileTask::class) {
    outputFilename = "docker-compose-staging.yml"
    configure(serverPort = 9090, clientPort = 8080)
}

val createDockerComposeProductionFile by tasks.creating(DockerComposeFileTask::class) {
    outputFilename = "docker-compose-production.yml"
    configure(serverPort = 90, clientPort = 80)
}
