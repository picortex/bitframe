package bitframe.client

import viewmodel.ViewModelStateExpectation
import viewmodel.expect

suspend fun <I, S> MicroScope<*, I, S>.expect(i: I): ViewModelStateExpectation<S> = viewModel.expect(i) as ViewModelStateExpectation<S>