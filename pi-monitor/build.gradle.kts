import docker.DockerComposeFileTask

plugins {
    `docker-compose`
}

val tmp = 3

val runWeb by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:runJsDebug")
}

val runServer by tasks.creating {
    dependsOn(":pi-monitor-server:runDebug")
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
