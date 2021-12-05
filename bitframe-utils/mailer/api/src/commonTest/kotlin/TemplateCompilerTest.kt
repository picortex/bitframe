import expect.expect
import kotlinx.collections.interoperable.listOf
import mailer.TemplateCompiler
import mailer.TemplateCompilerImpl
import kotlin.test.Test

class TemplateCompilerTest {
    val compiler: TemplateCompiler = TemplateCompilerImpl()

    @Test
    fun should_compile_a_parameterless_template() {
        expect(compiler.compile("Hello")).toBe("Hello")
    }

    @Test
    fun should_find_a_single_parameter() {
        expect(compiler.findParameters("{{name}}")).toBe(listOf(TemplateCompiler.Parameter("{{name}}")))
    }

    @Test
    fun should_find_a_single_parameter_with_spaces() {
        expect(compiler.findParameters("{{ name}}")).toBe(listOf(TemplateCompiler.Parameter("{{ name}}")))
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
                "names" to "Anderson"
            )
        )
        expect(actual).toBe("Hello Anderson, hope you are alright")
    }

    @Test
    fun should_interpolate_a_well_crafted_message() {
        val template = """
            Dear {{name}},
            I hope you are doing okay.
            <b>To do this</b>
            This message should remind you of our {{budget}} that we discussed on {{date}}
        """.trimIndent()
        val compiler = TemplateCompiler()
        println(
            compiler.compile(
                template,
                "name" to "Luge",
                "budget" to "TZS 5,000/=",
                "date" to "Wednesday"
            )
        )
    }
}