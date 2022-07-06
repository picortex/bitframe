@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Executor
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import logging.Logger
import presenters.forms.internal.FormViewModelConfigImpl
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

@JsExport
interface FormViewModelConfig<F : Form<*, P>, P> : ViewModelConfig<F> {
    val serializer: KSerializer<P>
    val form: F

    companion object {

        @JvmName("create")
        @JsName("_ignore_invoke_1")
        operator fun <F : Form<*, P>, P> invoke(
            form: F,
            serializer: KSerializer<P>,
            codec: StringFormat = ViewModelConfig.DEFAULT_CODEC,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER
        ): FormViewModelConfig<F, P> = FormViewModelConfigImpl(form, serializer, executor, logger, codec)

        @JvmSynthetic
        @JsName("_ignore_invoke_2")
        inline operator fun <F : Form<F, P>, reified P> invoke(
            form: F,
            codec: StringFormat = ViewModelConfig.DEFAULT_CODEC,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER
        ): FormViewModelConfig<F, P> = FormViewModelConfigImpl(form, serializer(), executor, logger, codec)
    }
}