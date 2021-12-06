package templater

import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface TemplateCompiler {
    fun findParameters(input: String): List<Parameter>
    fun compile(input: String, vararg parameters: Pair<String, Any>): String

    companion object {
        @JvmSynthetic
        operator fun invoke(): TemplateCompiler = TemplateCompilerImpl()

        @JvmStatic
        fun create() = invoke()
    }
}