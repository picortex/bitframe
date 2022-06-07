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
        "component", "_init_", "factory", "hashCode()", "toString",
        "copy", "equals", "serializer()", "descriptor: kotlinx.",
        "doNotUseIt", "**/> */", "_ignore_", "constructor", " config"
    )

    @get:Input
    var codeToBeReplaced = listOf(
        "abstract class" to "abstract_class",
        "abstract " to "",
        "abstract_class" to "abstract class",
        "value?: Nullable" to "value: Nullable"
    )

    @get:Input
    var codeblocksToBeRemoved = listOf(
        "static get Companion(): {",
        "static get ${'$'}serializer(): {",
        "class ${'$'}serializer<"
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

        var linesWithRemovedCodeBlocks = lines
        for (block in codeblocksToBeRemoved) {
            linesWithRemovedCodeBlocks = removeCodeBlock(linesWithRemovedCodeBlocks, block)
        }

        val filteredLines = linesWithRemovedCodeBlocks.filter { line ->
            !linesToBeRemoved.any { content -> line.contains(content) }
        }.map {
            changeNullables(it)
        }

        var replacedLines = filteredLines
        for (pair in codeToBeReplaced) {
            replacedLines = replacedLines.map { replace(it, pair) }
        }

        outputFile.apply {
            delete()
            createNewFile()
            replacedLines.forEach { appendText("$it\n") }
        }
    }

    private fun replace(line: String, pair: Pair<String, String>): String {
        if (!line.contains(pair.first)) return line
        return line.replace(pair.first, pair.second)
    }

    private fun changeNullables(line: String): String {
        if (!line.contains(": Nullable<")) return line
        val stage1 = line.replace(": Nullable<", "?: Nullable<")
        return stage1.replace(")?: Nullable<", "): Nullable<")
    }

    private fun removeCodeBlock(lines: List<String>, block: String): List<String> {
        val blocks = mutableListOf<String>()
        var index = 0
        while (index < lines.size) {
            val line = lines[index]
            if (line.contains(block)) {
                while (true) {
                    index++
                    if (lines[index].contains("}")) break
                }
            } else {
                blocks.add(line)
            }
            index++
        }
        return blocks
    }
}