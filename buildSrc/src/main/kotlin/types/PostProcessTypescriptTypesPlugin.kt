package types

import org.gradle.api.Plugin
import org.gradle.api.Project

open class PostProcessTypescriptTypesPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.afterEvaluate {
        val purifyTask = tasks.create("purifyTypes", PurifyTypesTask::class.java)
        val compileTask = tasks.findByName("productionLibraryCompileSync")
        purifyTask.mustRunAfter(compileTask)
    }
}