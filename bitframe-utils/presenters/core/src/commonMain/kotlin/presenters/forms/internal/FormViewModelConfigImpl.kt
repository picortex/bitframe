package presenters.forms.internal

import koncurrent.Executor
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat
import logging.Logger
import presenters.forms.Form
import presenters.forms.FormViewModelConfig
import viewmodel.ViewModelConfig

internal class FormViewModelConfigImpl<F : Form<*, P>, P>(
    override val form: F,
    override val serializer: KSerializer<P>,
    override val executor: Executor,
    override val logger: Logger,
    override val codec: StringFormat
) : FormViewModelConfig<F, P>, ViewModelConfig<F> by ViewModelConfig(form, executor, logger) {
    override val api: F get() = form
}