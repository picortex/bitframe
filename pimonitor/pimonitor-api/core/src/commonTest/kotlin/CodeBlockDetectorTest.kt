import expect.expect
import kotlin.test.Test

class CodeBlockDetectorTest {
    val lines = """
        val x = 5
        
        class John {
        
        }
    """.trimIndent().split("\n")

    @Test
    fun can_detect_a_block_size() {
        expect(lines.size).toBe(5)
    }

    @Test
    fun can_return_a_block_range_to_remove() {
        val range = detectCodeBlockNumberOf("class John")
        expect(range).toBe(4..5)
    }

    fun detectCodeBlockNumberOf(block: String): IntRange {
        TODO()
    }
}