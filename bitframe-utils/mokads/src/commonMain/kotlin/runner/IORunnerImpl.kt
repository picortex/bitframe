package runner

expect class IORunnerImpl<in I, out O>(block: (input: I) -> O) : IORunner<I, O>