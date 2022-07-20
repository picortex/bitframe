@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import viewmodel.ViewModelConfig
import kotlinx.serialization.serializer
import presenters.forms.internal.FormConfigImpl

inline fun <F : BaseForm<*, P>, reified P> ViewModelConfig<*>.toFormViewModelConfig(
    form: F
): BaseFormViewModelConfig<F, P> = BaseFormViewModelConfig(
    form, serializer(), codec, executor, logger
)

inline fun <reified P> ViewModelConfig<*>.toFormConfig(
    exitOnSubmitted: Boolean = FormConfig.DEFAULT_EXIT_ON_SUBMITTED
): FormConfig<P> = FormConfigImpl(
    serializer(), executor, logger, codec, exitOnSubmitted
)