package bitframe

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class BitframePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create<BitframeExtension>("bitframe", project)
        project.tasks.create<GenerateBitframeClientCode>("generateBitframeClientCode")
    }
}