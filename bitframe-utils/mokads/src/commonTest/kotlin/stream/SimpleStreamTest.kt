package stream

import expect.expect
import kotlin.test.Test

class SimpleStreamTest {

    @Test
    fun should_instantiate_a_stream_easily() {
        val s = stream {
            println("Stating stream")
            send(1)
            println("Ending stream")
        }
    }

    @Test
    fun should_collect_values_from_stream_easily() {
        val s = stream {
            println("Starting stream")
            println("Sending 1")
            send(1)
            println("Sending 2")
            send(2)
            println("Ending stream")
        }

        var collected = 0
        s.collect {
            println("Collecting $it")
            collected = it
        }

        expect(collected).toBe(2)
    }

    @Test
    fun should_collect_values_that_a_ran_asynchronously() {
        val s = stream {
            launch {
                println("Starting stream")
                println("Sending 1")
                send(1)
            }
            launch {
                println("Sending 2")
                send(2)
                println("Ending stream")
            }
        }

        var collected = 0
        s.collect {
            println("Collecting $it")
            collected = it
        }

        println("Finished sending and collecting")
        expect(collected).toBe(2)
    }
}