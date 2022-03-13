package runner

actual class IORunnerImpl<in I, out O> actual constructor(block: (input: I) -> O) : IORunner<I, O> {
    private val block = block
//    private val worker = Wor

    override fun start(input: I) {
        block(input)
    }

    override fun run(input: I): O = block(input)
}