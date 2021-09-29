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
        val compilation = compilations.create("test${testName.capitalize()}").apply {
            defaultSourceSet {
                this.kotlin.srcDir("$targetSource/test/$testName/kotlin")
                resources.srcDir("$targetSource/test/$testName/resources")
                dependencies {
                    implementation(test.runtimeDependencyFiles)
                    runtimeOnly("org.jetbrains.kotlin:kotlin-test-js-runner:1.5.30")
                }
            }
        }

        val binary = binaries.executable(compilation).first() as JsIrBinary

        project.afterEvaluate {

            val defaultTestTask = project.tasks.findByName("jsBrowserTest") as? KotlinJsTest
                ?: project.tasks.findByName("jsNodeTest") as? KotlinJsTest
                ?: throw Exception("Can't find default task")

            tasks.create("${this@testing.name}${testName.capitalize()}Test", KotlinJsTest::class.java, compilation).apply {
                dependsOn(compilation.compileKotlinTask)
                testFramework = defaultTestTask.testFramework
                inputFileProperty.set(project.layout.file(
                    binary.linkSyncTask.map {
                        it.destinationDir
                            .resolve(binary.linkTask.get().outputFileProperty.get().name)
                    }
                ))
                val nodeJs = project.rootProject.extensions.getByType(NodeJsRootExtension::class.java)
//            binaryResultsDirectory.set(defaultTestTask.binaryResultsDirectory)
                dependsOn(nodeJs.npmInstallTaskProvider, nodeJs.nodeJsSetupTaskProvider)
                binaryResultsDirectory.set(binary.linkSyncTask.get().destinationDir)
                reports.html.outputLocation.set(defaultTestTask.reports.html.outputLocation)
                reports.junitXml.outputLocation.set(defaultTestTask.reports.junitXml.outputLocation)
            }
        }
    }
}