package pimonitor.client

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import logging.console
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class TestStep<out O : Any>(
    val index: Int,
    val name: String,
    val ignore: Boolean,
    val block: suspend TestScope.() -> O
) {
    private lateinit var result: O
    val output get() = result
    internal suspend fun execute(scope: TestScope) {
        result = block(scope)
    }
}

class TestSequence {
    internal val steps = mutableListOf<TestStep<*>>()
    val time = Clock.System.now()

    fun <O : Any> xstep(name: String, block: suspend TestScope.() -> O): TestStep<O> {
        val step = TestStep(
            index = steps.size + 1,
            name = name,
            ignore = true,
            block = block
        )
        steps += step
        return step
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <O : Any> step(name: String, block: suspend TestScope.() -> O): TestStep<O> {
        val step = TestStep(
            index = steps.size + 1,
            name = name,
            ignore = false,
            block = block
        )
        steps += step
        return step
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun runSequence(block: suspend TestSequence.() -> Unit): TestResult = runTest {
    val sequence = TestSequence().apply { block() }
    for (step in sequence.steps) try {
        if (!step.ignore) step.execute(this) else console.warn("[IGNORING] STEP ${step.index}: ${step.name}")
    } catch (err: Throwable) {
        val message = buildString {
            appendLine("[SEQUENCE FAILURE]")
            appendLine("STEP ${step.index}: ${step.name}")
            appendLine("CAUSE : ${err.message}")
        }
        throw Throwable(message, err)
    }
}