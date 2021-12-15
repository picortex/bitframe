package templater

class ParameterNotFoundException(
    val param: Parameter,
    val params: Map<String, Any>
) : RuntimeException(
    """Template Parameter ${param.name} was found in template but it's value isn't provided during compilation compilation Provided Params are: ${params.entries.joinToString { "${it.key}=${it.value}" }}""".trimIndent()
)