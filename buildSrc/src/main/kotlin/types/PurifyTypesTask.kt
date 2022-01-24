package types

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

open class PurifyTypesTask : DefaultTask() {
    @get:InputDirectory
    var directory: File = File(project.buildDir, "compileSync/main/productionLibrary/kotlin")

    @get:Input
    var contentsToBeRemoved = listOf(
        "component", "_init_", "factory", "hashCode()", "toString", "copy", "equals", "serializer()", "constructor", "descriptor: kotlinx."
    )

    @get:InputFile
    val inputFile: File?
        get() = directory.listFiles().firstOrNull {
            it.name.contains(".d.ts")
        }

    @get:OutputFile
    val outputFile: File
        get() = File(directory, inputFile?.name)

    @TaskAction
    fun process() {
        val lines = inputFile?.readLines() ?: error("Couldn't get files")
        println("Before: ${lines.size}")
        val filteredLines = lines.filter { line ->
            !contentsToBeRemoved.any { content -> line.contains(content) }
        }
        println("After : ${filteredLines.size}")
        println(outputFile.absolutePath)
        outputFile.apply {
            createNewFile()
            filteredLines.forEach { appendText("$it\n") }
        }
//        filteredLines.forEach {
//            println(it)
//        }
    }
}