package runner

import kotlin.test.Test

class IORunnerTest {
    @Test
    fun can_try_to_run_a_runner_asynchronously() {
        val sum = runner { _: Int ->
            println("Summing")
            2 + 2
            println("Finished summing")
        }
        println("Starting")
        sum.start(0)
    }
}