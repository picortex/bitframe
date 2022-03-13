package runner

actual class IORunnerImpl<in I, out O> actual constructor(block: (input: I) -> O) : IORunner<I, O> {
    private val block = block

    override fun start(input: I) {
        setTimeout { block(input) }
    }

    override fun run(input: I): O = block(input)
}