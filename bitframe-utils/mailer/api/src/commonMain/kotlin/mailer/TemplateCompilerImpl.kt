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
            val providedParam = inputParamMaps[param.name]?.toString()
                ?: error("Param ${param.name} was found in template but it's value isn't provided during compilation compilation")
            output = output.replace(param.raw, providedParam)
        }
        return output
    }
}