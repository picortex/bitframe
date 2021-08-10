package docker

import org.gradle.api.Plugin
import org.gradle.api.Project

open class DockerComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val createFile = target.tasks.create("createDockerComposeFile", DockerComposeFileTask::class.java)
        val composeUpCommands = listOf("docker-compose", "up", "--build")
        target.tasks.create("dockerComposeUp", DockerComposeCommandTask::class.java, createFile, composeUpCommands)
        val composeDownCommands = listOf("docker-compose", "down")
        target.tasks.create("dockerComposeDown", DockerComposeCommandTask::class.java, createFile, composeDownCommands)
        val composePushCommands = listOf("docker-compose", "push")
        target.tasks.create("dockerComposePush", DockerComposeCommandTask::class.java, createFile, composePushCommands)
            .apply {
                dependsOn("dockerComposeBuild")
            }
        target.tasks.create("dockerStackDeploy", DockerStackDeployTask::class.java, createFile)
    }
}