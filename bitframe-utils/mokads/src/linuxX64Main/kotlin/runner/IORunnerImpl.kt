package runner

import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

actual class IORunnerImpl<in I, out O> actual constructor(block: (input: I) -> O) : IORunner<I, O> {
    private val block = block

    //    private val worker = Wor
    companion object {
        private val w = Worker.start(name = "IORunnerWorker")
    }

    class WorkerParams<I, O>(val input: I, val block: (input: I) -> O)

    override fun start(input: I) {
        w.execute(TransferMode.SAFE, { WorkerParams(input, block) }) { params -> params.block(params.input) }
    }

    override fun run(input: I): O = block(input)
}