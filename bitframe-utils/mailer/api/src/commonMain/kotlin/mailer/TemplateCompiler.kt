package mailer

import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface TemplateCompiler {
    fun findParameters(input: String): List<Parameter>
    fun compile(input: String, vararg parameters: Pair<String, Any>): String

    data class Parameter(
        val raw: String
    ) {
        val name by lazy { raw.removeSurrounding(prefix = "{{", suffix = "}}").trim() }
    }

    companion object {
        @JvmSynthetic
        operator fun invoke(): TemplateCompiler = TemplateCompilerImpl()

        @JvmStatic
        fun create() = invoke()
    }
}