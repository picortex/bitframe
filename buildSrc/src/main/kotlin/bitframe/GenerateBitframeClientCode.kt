package bitframe

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

open class GenerateBitframeClientCode : DefaultTask() {
    private val ext = project.extensions.getByType<BitframeExtension>()
    private val client = HttpClient { }

    @TaskAction
    fun generate() = runBlocking<Unit> {
        println("requesting from ${ext.url}")
        val json = client.get<String>(ext.url + "/people")
        println(json)
    }
}