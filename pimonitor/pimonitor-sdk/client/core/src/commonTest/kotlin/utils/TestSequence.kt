package utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock

class TestStep<out O>(
    val index: Int,
    val name: String,
    val block: suspend TestScope.() -> O
)

class TestSequence {
    internal val steps = mutableListOf<TestStep<*>>()
    val time = Clock.System.now()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <O> step(name: String, block: suspend TestScope.() -> O): TestStep<O> {
        val step = TestStep(
            index = steps.size + 1,
            name = name,
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
        step.block(this)
    } catch (err: Throwable) {
        throw Throwable("STEP ${step.index} FAILED: ${step.name}", err)
    }
}