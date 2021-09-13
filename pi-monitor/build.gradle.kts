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

fun DockerComposeFileTask.configure(port: Int) {
    version(3.8)
    service("server-application") {
        image("${vars.dev.server.ip}:1030/pi-monitor:${vers.bitframe.current}")
        ports(port to 8080)
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
    configure(port = 80)
}

val createDockerComposeProductionFile by tasks.creating(DockerComposeFileTask::class) {
    outputFilename = "docker-compose-production.yml"
    configure(port = 90)
}
