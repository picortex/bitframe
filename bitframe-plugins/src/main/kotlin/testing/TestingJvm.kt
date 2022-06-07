package testing

import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

fun KotlinJvmTarget.testing(vararg names: String) {
    val targetSource = "src/$name"
    val main = compilations.get("main").apply {
        defaultSourceSet {
            this.kotlin.srcDir("$targetSource/main/kotlin")
            resources.srcDir("$targetSource/main/resources")
        }
    }

    val test = compilations.get("test")

    names.forEach {
        compilations.create("test${it.capitalize()}").apply {
            defaultSourceSet {
                kotlin.srcDir("$targetSource/test/$it/kotlin")
                resources.srcDir("$targetSource/test/$it/resources")
                dependencies {
                    implementation(test.runtimeDependencyFiles)
                }
            }

            project.tasks.register("${this@testing.name}${it.capitalize()}Test", Test::class) {
                useJUnitPlatform()
                classpath = compileDependencyFiles + runtimeDependencyFiles + output.allOutputs
                testClassesDirs = output.classesDirs
            }
        }
    }
}