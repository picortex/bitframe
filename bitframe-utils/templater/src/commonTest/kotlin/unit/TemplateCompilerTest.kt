package unit

import expect.expect
import templater.Parameter
import templater.TemplateCompiler
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore // TODO: Compiler RegEx in JS is breaking
class TemplateCompilerTest {
    val compiler: TemplateCompiler = TemplateCompiler()

    @Test
    fun should_compile_a_parameterless_template() {
        expect(compiler.compile("Hello")).toBe("Hello")
    }

    @Test
    fun should_find_a_single_parameter() {
        expect(compiler.findParameters("{{name}}")).toBe(listOf(Parameter("{{name}}")))
    }

    @Test
    fun should_find_a_single_parameter_with_spaces() {
        expect(compiler.findParameters("{{ name}}")).toBe(listOf(Parameter("{{ name}}")))
    }

    @Test
    fun should_find_multiple_parameters() {
        expect(compiler.findParameters("{{p}} {{q}}")).toBeOfSize(2)
    }

    @Test
    fun should_compile_a_template_only_input() {
        expect(compiler.compile("{{name}}", "name" to "Anderson")).toBe("Anderson")
    }

    @Test
    fun should_work_for_different_variables() {
        val template = "{{ greeting }} {{name}}, hope you are alright"
        val actual = compiler.compile(
            template,
            *arrayOf(
                "greeting" to "Hello",
                "name" to "Anderson"
            )
        )
        expect(actual).toBe("Hello Anderson, hope you are alright")
    }
}