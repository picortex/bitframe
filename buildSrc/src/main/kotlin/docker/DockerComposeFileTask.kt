package docker

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import java.io.File
import java.lang.StringBuilder

open class DockerComposeFileTask : DefaultTask() {

    init {
        group = "docker"
    }

    @Input
    val structure = mutableMapOf<String, Any>(
        "version" to "3.8"
    )

    @OutputDirectory
    var outputDir: File = File(project.buildDir, "docker").apply {
        if (!exists()) mkdirs()
    }

    @OutputFile
    var outputFile: File = File(outputDir, "docker-compose.yml")

    @OutputFile
    var deployFile: File = File(outputDir, "docker-compose-deploy.yml")

    fun StringBuilder.appendNested(level: Int, obj: Map<String, Any>, excludeBuild: Boolean) {
        for ((key, value) in obj) {
            if (excludeBuild && key == "build") continue
            append("  ".repeat(level))
            append("$key:")
            when (value) {
                is String, is Number -> append(" $value\n")
                is Map<*, *> -> {
                    append("\n")
                    appendNested(level + 1, value as Map<String, Any>, excludeBuild)
                }
                is List<*> -> {
                    for (items in value) {
                        append("\n")
                        append("  ".repeat(level + 1))
                        append("- $items")
                    }
                    append("\n")
                }
            }
        }
    }

    private fun formatted(map: Map<String, Any>, excludeBuild: Boolean) = buildString {
        append("version: ${map["version"]}\n\n")
        append("services:\n")
        val services = map["services"] as List<Map<String, Any>>
        for (service in services) {
            appendNested(level = 1, service, excludeBuild)
            if (service != services.lastOrNull()) append("\n")
        }
    }

    @TaskAction
    fun createFile() {
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        outputFile.writeText(formatted(structure, excludeBuild = false))
        if (!deployFile.exists()) {
            deployFile.createNewFile()
        }
        deployFile.writeText(formatted(structure, excludeBuild = true))
    }

//    @OptIn(ExperimentalStdlibApi::class)
//    private fun removeBuild(map: Map<String, Any>): Map<String, Any> {
//        val services = map["services"] as List<Map<String, Any>>
//        val newServices = buildList<Map<String, Any>> {
//            for (service in services) {
//                val s = service.toMutableMap().entries.fir
//                println(s)
//                s.remove("build")
//                add(s)
//            }
//        }
//        val newMap = map.toMutableMap()
//        newMap["services"] = newServices
//        return newMap
//    }

    @get:Internal
    var version: Double
        get() = structure["version"].toString().toDouble()
        set(value) {
            version(value)
        }

    fun version(value: Double) {
        structure["version"] = """"$value""""
    }

    fun service(
        name: String,
        builder: Service.() -> Unit
    ) = Service(name).also {
        it.builder()
        val services = structure.getOrDefault("services", mutableListOf<Any>()) as MutableList<Any>
        services.add(mapOf(it.name to it.structure))
        structure["services"] = services
    }

    class Service(
        val name: String
    ) {
        internal val structure = mutableMapOf<String, Any>()
        fun build(context: File) {
            structure["build"] = mapOf(
                "context" to context.absolutePath
            )
        }

        fun build(value: String) {
            structure["build"] = value
        }

        fun ports(vararg ports: Pair<Int, Int>) {
            structure["ports"] = ports.map { "${it.first}:${it.second}" }
        }

        fun put(key: String, value: Any) {
            structure[key] = value
        }

        operator fun set(key: String, value: Any) {
            structure[key] = value
        }

        fun image(value: String) {
            structure["image"] = value
        }

        fun container(name: String) {
            structure["container_name"] = name
        }
    }
}