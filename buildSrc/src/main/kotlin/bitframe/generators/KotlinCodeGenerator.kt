package bitframe.generators

import bitframe.BitframeExtension
import java.io.File

class KotlinCodeGenerator(
    val ext: BitframeExtension,
    val modules: List<Map<String, *>>
) : CodeGenerator {

    private fun File.generateActionParamClass(actionName: String, params: Map<String, *>) {
        appendText("\tclass ${actionName.capitalize()}Params(\n")
        for (param in params) {
            appendText("\t\tval ${param.key}: String,\n")
        }
        appendText("\t)\n")
    }

    private fun File.generateActionMethod(actionName: String, params: Map<String, *>) {
        appendText("\tfun ${actionName.toLowerCase()}(params: ${actionName.capitalize()}Params){\n")
        appendText("\t\t// TODO()\n")
        appendText("\t}\n")
    }

    override fun generate() = modules.forEach { module ->
        val moduleName = module["name"] as? String ?: throw Exception("Module is missing a name")
        val sourceSet = File(ext.destination, ext.namespace.replace(".", "/")).apply { mkdirs() }
        val serviceName = "${moduleName}Service"
        val actions = module["actions"] as? List<Map<String, *>>
            ?: throw Exception("Module is missing actions block")
        File(sourceSet, "${serviceName}.kt").apply {
            createNewFile()
            writeText("package ${ext.namespace}\n\n")
            appendText("class $serviceName { \n")
            for (action in actions) {
                val actionName = action["name"] as? String ?: throw Exception("Action is missing a name")
                val params = action["params"] as? Map<String, Any?> ?: throw Exception("Actions is missing params")
                generateActionParamClass(actionName, params)
                generateActionMethod(actionName, params)
            }
            appendText("}\n")
        }
    }
}