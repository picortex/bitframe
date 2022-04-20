package pimonitor.client

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import logging.console

class TestSequence(
    @PublishedApi
    internal val scope: TestScope
) {
    @PublishedApi
    internal var steps = 0
    val time = Clock.System.now()

    inline fun <O : Any> xstep(name: String, block: TestScope.() -> O) {
        steps++
        console.info("SKIPPING STEP[$steps]: $name")
    }

    fun step(name: String) {
        steps++
        console.warn("SKIPPING STEP[$steps]: $name")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    inline fun <O : Any> step(name: String, block: TestScope.() -> O): O = try {
        steps++
        console.info("RUNNING STEP[$steps]: $name")
        val res = scope.block()
        console.log("FINISHED STEP[$steps]: $name")
        res
    } catch (err: Throwable) {
        val message = buildString {
            appendLine("[SEQUENCE FAILURE]")
            appendLine("STEP $steps: $name")
            appendLine("CAUSE : ${err.message}")
        }
        throw Throwable(message, err)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun runSequence(block: suspend TestSequence.() -> Unit): TestResult = runTest {
    TestSequence(this).apply { block() }
}