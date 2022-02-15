package types

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

open class PurifyTypesTask : DefaultTask() {

    companion object {
        @JvmField
        val DEFAULT_OUTPUT_FILENAME = "index.d.ts"

        @JvmField
        val DEFAULT_DIRECTORY = "publications/npm/js"
    }

    @get:InputDirectory
    var directory: File = File(project.buildDir, DEFAULT_DIRECTORY)

    @get:Input
    var linesToBeRemoved = listOf(
        "component", "_init_", "factory", "hashCode()", "toString", "copy", "equals", "serializer()", "descriptor: kotlinx.", "doNotUseIt"
    )

    @get:Input
    var codeblocksToBeRemoved = listOf(
        "static get Companion(): {"
    )

    @get:InputFile
    val inputFile: File?
        get() = directory.listFiles()?.firstOrNull {
            it.name.contains(".d.ts") && !it.name.contains(outputFile.name)
        }

    @get:OutputFile
    val outputFile: File
        get() = File(directory, DEFAULT_OUTPUT_FILENAME)

    @TaskAction
    fun process() {
        val lines = inputFile?.readLines() ?: error("Couldn't get files")
        val filteredLines = lines.filter { line ->
            !linesToBeRemoved.any { content -> line.contains(content) }
        }.map {
            changeNullables(it)
        }
        outputFile.apply {
            delete()
            createNewFile()
            filteredLines.forEach { appendText("$it\n") }
        }
    }

    private fun changeNullables(line: String): String {
        if (!line.contains(": Nullable<")) return line
        val stage1 = line.replace(": Nullable<", "?: Nullable<")
        return stage1.replace(")?: Nullable<", "): Nullable<")
    }
}