import org.hidetake.groovy.ssh.core.Remote
import org.hidetake.groovy.ssh.core.RunHandler
import org.hidetake.groovy.ssh.core.Service
import org.hidetake.groovy.ssh.operation.SftpOperations
import org.hidetake.groovy.ssh.session.SessionHandler
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import versioning.DualVersion

plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    id("tz.co.asoft.library") apply false
    id("dev.petuska.npm.publish") version vers.npmPublish
    id("com.bmuschko.docker-java-application") version vers.docker apply false
    id("org.jetbrains.dokka") version vers.dokka
    id("org.hidetake.ssh") version "2.10.1"
}

allprojects {
    repositories {
        publicRepos()
    }
    group = "com.picortex"
    version = vers.bitframe.stagingCurrent
}

val dokkaHtmlMultiModule by tasks.getting(DokkaMultiModuleTask::class) {
    moduleName.set("Bitframe Docs")
}

fun Service.runSessions(action: RunHandler.() -> Unit) =
    run(delegateClosureOf(action))

fun RunHandler.session(vararg remotes: Remote, action: SessionHandler.() -> Unit) =
    session(*remotes, delegateClosureOf(action))

fun SessionHandler.put(from: Any, into: Any) =
    put(hashMapOf("from" to from, "into" to into))

fun SessionHandler.sftp(block: SftpOperations.() -> Unit) = sftp(delegateClosureOf(block))

remotes {
    val picortex by creating {
        role("Dev Server")
        host = vars.dev.server.ip
        user = vars.dev.server.user
        password = vars.dev.server.password
    }
}

val String.safe get() = replace(".", "_")

fun SessionHandler.deploy(v: DualVersion) {
    execute("docker stack rm pimonitor-${v.name}-${v.previous.safe}")
    execute("docker stack rm pimonitor-${v.name}-${v.current.safe}")

    // copy current build file
    sftp {
        execute("mkdir /picortex/apps/pimonitor/${v.current.safe}/${v.name} -p")
        putFile(
            rootDir.resolve("pimonitor/server/app/build/docker/docker-compose-${v.name}.yml").absolutePath,
            "/picortex/apps/pimonitor/${v.current.safe}/${v.name}/docker-compose.yml"
        )
    }

    // deploy new stack
    execute("mkdir /picortex/apps/pimonitor/${v.current.safe}/${v.name} -p")
    executeScript(
        """
        cd /picortex/apps/pimonitor/${v.current.safe}/${v.name}
        docker-compose pull
        docker stack deploy -c docker-compose.yml pimonitor-${v.name}-${v.current.safe}
        """.trimIndent()
    )
}

val deployStaging by tasks.creating {
    dependsOn(":pimonitor-server-app:dockerPushToPiCortex")
    dependsOn(":pimonitor-server-app:createDockerComposeStagingFile")
    doLast {
        ssh.runSessions {
            val picortex by remotes
            session(picortex) {
                deploy(vers.bitframe.staging)
            }
        }
    }
}

val deployProduction by tasks.creating {
    dependsOn(":pimonitor-server-app:dockerPushToPiCortex")
    dependsOn(":pimonitor-server-app:createDockerComposeProductionFile")
    doLast {
        ssh.runSessions {
            val picortex by remotes
            session(picortex) {
                deploy(vers.bitframe.production)
            }
        }
    }
}