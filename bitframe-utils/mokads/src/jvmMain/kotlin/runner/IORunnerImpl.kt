package runner

import java.util.concurrent.Executors

actual class IORunnerImpl<in I, out O> actual constructor(block: (input: I) -> O) : IORunner<I, O> {
    private val block = block
    private val executor = Executors.newSingleThreadExecutor()

    override fun start(input: I) {
        executor.execute { block(input) }
    }

    override fun run(input: I): O = block(input)
}