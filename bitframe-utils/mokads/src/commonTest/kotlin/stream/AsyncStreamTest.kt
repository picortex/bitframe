package stream

import expect.expect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.test.Test

class AsyncStreamTest {

    @Test
    fun should_instantiate_a_stream_easily() {
        val s = asyncStream {
            println("Stating stream")
            send(1)
            println("Ending stream")
        }
    }

    @Test
    fun should_collect_values_from_stream_easily() = runTest {
        val s = asyncStream {
            println("Starting stream")
            println("Sending 1")
            send(1)
            println("Sending 2")
            send(2)
            println("Ending stream")
        }

        var colectees = 0
        s.collect {
            println("Collecting $it")
            colectees = it
        }
        println("Finished collecting")
        withContext(Dispatchers.Default) { delay(100) }
        expect(colectees).toBe(2)
    }

    @Test
    fun should_collect_values_that_a_ran_asynchronously() = runTest {
        val s = asyncStream {
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
        withContext(Dispatchers.Default) { delay(100) }
        expect(collected).toBe(2)
    }
}