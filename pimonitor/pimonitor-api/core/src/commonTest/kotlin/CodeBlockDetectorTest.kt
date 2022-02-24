import expect.expect
import kotlin.test.Test

// Test for purify plugin
class CodeBlockDetectorTest {
    val lines = """
        val x = 5
        
        class John {
        
        }
        
        class Peter {
       
        }
        
        class John {
        
        }
    """.trimIndent().split("\n")

    val result = """
        val x = 5


        class Peter {

        }
        
    """.trimIndent().split("\n")

    @Test
    fun can_detect_a_block_size() {
        expect(lines.size).toBe(13)
    }

    fun printLines(lines: List<String>) {
        for (line in lines) println(line)
    }

    @Test
    fun can_return_a_block_range_to_remove() {
        val output = removeCodeBlock(lines, "class John")
        println("Before")
        printLines(lines)
        println("After")
        printLines(output)
        expect(output).toBe(result)
    }

    fun removeCodeBlock(lines: List<String>, block: String): List<String> {
        val blocks = mutableListOf<String>()
        var index = 0
        while (index < lines.size) {
            val line = lines[index]
            if (line.contains(block)) {
                while (true) {
                    index++
                    if (lines[index].contains("}")) break
                }
            } else {
                blocks.add(line)
            }
            index++
        }
        return blocks
    }
}