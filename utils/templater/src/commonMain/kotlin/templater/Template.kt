package templater

import kotlin.jvm.JvmOverloads

class Template @JvmOverloads constructor(
    val content: String,
    val compiler: TemplateCompiler = TemplateCompiler()
) {
    fun compile(vararg params: Pair<String, Any>): String = compiler.compile(content, *params)
}