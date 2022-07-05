@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Executor
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.StringFormat
import logging.Logger
import presenters.forms.internal.FormViewModelConfigImpl
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

@JsExport
interface FormViewModelConfig<F : Form<*, P>, P> : ViewModelConfig<F> {
    val codec: StringFormat
    val serializer: KSerializer<P>
    val form: F

    companion object {
        operator fun <F : Form<*, P>, P> invoke(
            form: F,
            serializer: KSerializer<P>,
            codec: StringFormat,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER
        ): FormViewModelConfig<F, P> = FormViewModelConfigImpl(form, serializer, executor, logger, codec)
    }
}