import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import docker.DockerComposeFileTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
    id("tz.co.asoft.applikation")
    `docker-compose`
}

application {
    mainClass.set("pimonitor.server.MainKt")
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
                api(projects.pimonitorSdkServerCore)
                api(projects.bitframeSdkServerKtor)
                api(projects.mailerSmtp)
                api(projects.bitframeDaoMock)
                api(projects.bitframeDaoMongo)
            }
        }

        val test by getting {
            dependencies {
                implementation(projects.bitframeSdkServerTest)
                implementation(asoft.expect.coroutines)
            }
        }
    }
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("installDistRelease")
    from("openjdk:17.0.2-jdk")
    runCommand("mkdir /app /app/public")
    destFile.set(file("build/binaries/Dockerfile"))
    copyFile("./release", "/app")
//    copyFile("./public", "/app/public")
    workingDir("/app")
    exposePort(8080)
    defaultCommand("./bin/pimonitor-app-server", "/app/public")
}

val createDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    inputDir.set(file("build/binaries"))
    images.addAll("pimonitor:${vers.bitframe.stagingCurrent}")
}

fun dockerPushTo(remote: String) = tasks.creating(Exec::class) {
    dependsOn(createDockerImage)
    val localTag = "pimonitor:${vers.bitframe.stagingCurrent}"
    val remoteName = "$remote/pimonitor:${vers.bitframe.stagingCurrent}"
    commandLine("docker", "tag", localTag, remoteName)
    doLast {
        exec { commandLine("docker", "push", remoteName) }
    }
}

val dockerPushToAndylamax by dockerPushTo("localhost:1030")

val dockerPushToPiCortex by dockerPushTo("${vars.dev.server.ip}:1030")

val run by tasks.getting(JavaExec::class) {
    val public = properties.getOrDefault("public", "/default")
    args = listOf(public.toString())
}

fun DockerComposeFileTask.configure(port: Int) {
    version(3.8)
    service("database") {
        image("mongo:5.0.6")
        set(
            "environment", mapOf(
                "MONGO_INITDB_ROOT_USERNAME" to "root",
                "MONGO_INITDB_ROOT_PASSWORD" to "example"
            )
        )
        ports((port - 1) to 27017)
    }
    service("server-app") {
        image("${vars.dev.server.ip}:1030/pimonitor:${vers.bitframe.stagingCurrent}")
        set("restart", "always")
        set(
            "depends_on", listOf(
                "database"
            )
        )
        ports(port to 8080)
    }
}

val createDockerComposeStagingFile by tasks.creating(DockerComposeFileTask::class) {
    outputFilename = "docker-compose-staging.yml"
    configure(port = 90)
}

val createDockerComposeProductionFile by tasks.creating(DockerComposeFileTask::class) {
    outputFilename = "docker-compose-production.yml"
    configure(port = 80)
}

// set versions fro github-actions
afterEvaluate {
    fun writeVersion(name: String, value: String) {
        val dir = file("build/versioning").apply { mkdirs() }
        val file = File(dir, "$name.txt").apply { if (!exists()) createNewFile() }
        val safe = File(dir, "${name}_safe.txt").apply { if (!exists()) createNewFile() }
        file.writeText(value)
        safe.writeText(value.replace(".", "_"))
    }
    writeVersion("staging_current", vers.bitframe.stagingCurrent)
    writeVersion("staging_previous", vers.bitframe.stagingPrevious)
    writeVersion("production_current", vers.bitframe.productionCurrent)
    writeVersion("production_previous", vers.bitframe.productionPrevious)
}
