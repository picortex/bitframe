@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Fulfilled
import koncurrent.Rejected
import koncurrent.later.finally
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import viewmodel.ViewModel
import kotlin.js.JsExport

open class FormViewModel<F : BaseForm<*, P>, P>(
    val config: BaseFormViewModelConfig<F, P>
) : ViewModel<BaseFormState<F>>(config.of(BaseFormState.Fillable(config.api))) {
    private val form get() = config.api
    private val codec get() = config.codec

    fun cancel() {
        try {
            form.cancel()
        } catch (err: Throwable) {
            ui.value = BaseFormState.Failure(form, err)
        }
    }

    fun submit() = try {
        ui.value = BaseFormState.Submitting(form)
        form.validate()
        if (form.fields.areNotValid) error("Some values are invalid")
        val encoded = codec.encodeToString(
            serializer = MapSerializer(String.serializer(), String.serializer()),
            value = form.fields.values as Map<String, String>
        )
        form.submit(codec.decodeFromString(config.serializer, encoded)).finally {
            ui.value = when (it) {
                is Fulfilled -> BaseFormState.Submitted(form)
                is Rejected -> BaseFormState.Failure(form, it.cause)
            }
            ui.value
        }
    } catch (err: Throwable) {
        ui.value = BaseFormState.Failure(form, err)
    }
}