@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import viewmodel.ViewModelConfig
import kotlinx.serialization.serializer

inline fun <F : Form<*, P>, reified P> ViewModelConfig<*>.toFormViewModelConfig(
    form: F
): FormViewModelConfig<F, P> = FormViewModelConfig(
    form, serializer(), codec, executor, logger
)