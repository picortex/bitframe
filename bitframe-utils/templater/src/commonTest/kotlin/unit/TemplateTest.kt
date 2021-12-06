package unit

import expect.expect
import templater.Template
import kotlin.test.Test

class TemplateTest {
    @Test
    fun should_interpolate_a_well_crafted_message() {
        val template = Template(
            """
            Dear {{name}},
            I hope you are doing okay.
            <b>To do this</b>
            This message should remind you of our {{budget}} that we discussed on {{date}}
        """.trimIndent()
        )

        val output = template.compile(
            "name" to "Luge",
            "budget" to "TZS 5,000/=",
            "date" to "Wednesday"
        )

        val expected = """
            Dear Luge,
            I hope you are doing okay.
            <b>To do this</b>
            This message should remind you of our TZS 5,000/= that we discussed on Wednesday
        """.trimIndent()
        expect(expected).toBe(output)
    }
}