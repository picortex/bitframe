package mailer

import kotlinx.collections.interoperable.mutableListOf

class TemplateCompilerImpl : TemplateCompiler {
    override fun findParameters(input: String): List<TemplateCompiler.Parameter> {
        val regex = Regex("""\{{2}\s?\w+\s?}{2}""")
        val params = mutableListOf<TemplateCompiler.Parameter>()
        regex.findAll(input).forEach { m ->
            params.add(TemplateCompiler.Parameter(m.value))
        }
        return params
    }

    override fun compile(input: String, vararg parameters: Pair<String, Any>): String {
        val params = findParameters(input)
        val inputParamMaps = parameters.toMap()
        var output = input
        params.forEach { param ->
            output = output.replace(
                oldValue = param.raw,
                newValue = inputParamMaps[param.name]?.toString() ?: throw ParameterNotFound(param, inputParamMaps)
            )
        }
        return output
    }

    class ParameterNotFound(
        val param: TemplateCompiler.Parameter,
        val params: Map<String, Any>
    ) : RuntimeException(
        """Template Parameter ${param.name} was found in template but it's value isn't provided during compilation compilation Provided Params are: ${params.entries.joinToString { "${it.key}=${it.value}" }}""".trimIndent()
    )
}