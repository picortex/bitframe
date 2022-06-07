package testing

import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl
import org.jetbrains.kotlin.gradle.targets.js.ir.JsIrBinary
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest

fun KotlinJsTargetDsl.testing(vararg names: String) {
    val targetSource = "src/$name"
    val main = compilations.get("main").apply {
        defaultSourceSet {
            this.kotlin.srcDir("$targetSource/main/kotlin")
            resources.srcDir("$targetSource/main/resources")
        }
    }

    val test = compilations.get("test")

    names.forEach { testName ->
        val compilation = compilations.create(testName.capitalize()).apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/test/$testName/kotlin")
                resources.srcDir("$targetSource/test/$testName/resources")
                dependencies {
                    implementation(test.runtimeDependencyFiles)
                    implementation(main.runtimeDependencyFiles)
                }
            }
        }

        testRuns.create(compilation.name) {
            setExecutionSourceFrom(compilation)
        }
    }
}