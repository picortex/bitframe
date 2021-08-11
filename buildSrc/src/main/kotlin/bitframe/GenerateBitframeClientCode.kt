package bitframe

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.mapper.Mapper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import bitframe.generators.KotlinCodeGenerator
import bitframe.generators.CodeGenerator

open class GenerateBitframeClientCode : DefaultTask() {
    private val ext = project.extensions.getByType<BitframeExtension>()
    private val client = HttpClient { }

    @TaskAction
    fun generate() = runBlocking<Unit> {
        println("requesting from ${ext.url}")
        val json = client.get<String>(ext.url + "/info")
        val modules = Mapper.decodeListFromString(json)
        val generator: CodeGenerator = when (ext.language) {
            Language.Kotlin -> KotlinCodeGenerator(ext, modules)
        }
        generator.generate()
    }
}