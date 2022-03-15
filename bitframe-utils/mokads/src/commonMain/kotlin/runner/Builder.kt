package runner

fun <I, O> runner(block: (input: I) -> O): IORunner<I, O> = IORunnerImpl(block)