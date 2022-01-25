package types

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File

open class PurifyTypesTask : DefaultTask() {
    @get:InputDirectory
    var directory: File = File(project.buildDir, "publications/npm/js")

    @get:Input
    var contentsToBeRemoved = listOf(
        "component", "_init_", "factory", "hashCode()", "toString", "copy", "equals", "serializer()", "descriptor: kotlinx."
    )

    @get:InputFile
    val inputFile: File?
        get() = directory.listFiles()?.firstOrNull {
            it.name.contains(".d.ts")
        }

    @get:OutputFile
    val outputFile: File
        get() = File(directory, "index.d.ts")

    @TaskAction
    fun process() {
        val lines = inputFile?.readLines() ?: error("Couldn't get files")
        val filteredLines = lines.filter { line ->
            !contentsToBeRemoved.any { content -> line.contains(content) }
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
        return stage1.replace("()?: Nullable", "(): Nullable")
    }
}