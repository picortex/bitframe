package testing

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.ir.KotlinJsIrTarget
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

open class TestingPlugin : Plugin<Project> {

    companion object {
        const val KOTLIN_MULTIPLATFORM_PLUGIN_ID = "org.jetbrains.kotlin.multiplatform"
        fun Project.has(pluginId: String) = plugins.hasPlugin(pluginId)
    }

    private fun setupTestingFor(p: Project, kotlin: KotlinMultiplatformExtension, target: KotlinJsIrTarget) {
        val targetSource = "src/${target.name}"
        val main = target.compilations.get("main").apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/main/kotlin")
                resources.srcDir("$targetSource/main/resources")
            }
        }

        val test = target.compilations.get("test")

        kotlin.sourceSets.create("${target.name}TestIntegration")
        val testIntegration = target.compilations.create("testIntegration").apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/test/integration/kotlin")
                resources.srcDir("$targetSource/test/integration/resources")
                dependencies {
                    implementation(test.runtimeDependencyFiles)
                }
            }
        }

        p.tasks.register("${target.name}IntegrationTest", KotlinJsTest::class.java, testIntegration)
    }

    private fun setupTestingFor(p: Project, kotlin: KotlinMultiplatformExtension, target: KotlinJvmTarget) {
        val targetSource = "src/${target.name}"
        val main = target.compilations.get("main").apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/main/kotlin")
                resources.srcDir("$targetSource/main/resources")
            }
        }

        val test = target.compilations.get("test")

        kotlin.sourceSets.create("${target.name}TestIntegration")

        val testIntegration = target.compilations.create("testIntegration").apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/test/integration/kotlin")
                resources.srcDir("$targetSource/test/integration/resources")
                dependencies {
                    implementation(test.runtimeDependencyFiles)
                }
            }

            p.tasks.register("${target.name}IntegrationTest", Test::class) {
                useJUnitPlatform()
                classpath = compileDependencyFiles + runtimeDependencyFiles + output.allOutputs
                testClassesDirs = output.classesDirs
            }
        }
    }

    private fun setupMultiplatformTesting(p: Project) {
        val kotlin = p.extensions.getByName("kotlin") as KotlinMultiplatformExtension
        val targets = kotlin.targets
        val jvmTargets = targets.filterIsInstance<KotlinJvmTarget>()
        jvmTargets.forEach { setupTestingFor(p, kotlin, it) }
        val jsTargets = targets.filterIsInstance<KotlinJsIrTarget>()
        jsTargets.forEach { setupTestingFor(p, kotlin, it) }
    }

    override fun apply(target: Project) = when {
        target.has(KOTLIN_MULTIPLATFORM_PLUGIN_ID) -> setupMultiplatformTesting(target)
        else -> Unit
    }
}