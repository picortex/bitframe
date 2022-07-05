@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Fulfilled
import koncurrent.Rejected
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import viewmodel.ViewModel
import kotlin.js.JsExport

class FormViewModel<F : Form<*, P>, P>(
    val config: FormViewModelConfig<F, P>
) : ViewModel<FormState<F>>(config.of(FormState.Fillable(config.api))) {
    private val form get() = config.api
    private val codec get() = config.codec

    fun cancel() {
        try {
            form.cancel()
        } catch (err: Throwable) {
            ui.value = FormState.Failure(form, err)
        }
    }

    fun submit() = try {
        ui.value = FormState.Submitting(form)
        form.validate()
        if (form.fields.areNotValid) error("Some values are invalid")
        val encoded = codec.encodeToString(
            serializer = MapSerializer(String.serializer(), String.serializer()),
            value = form.fields.values as Map<String, String>
        )
        form.submit(codec.decodeFromString(config.serializer, encoded)).complete({
            ui.value = when (it) {
                is Fulfilled -> FormState.Submitted(form)
                is Rejected -> FormState.Failure(form, it.cause)
            }
            ui.value
        }, config.executor)
    } catch (err: Throwable) {
        ui.value = FormState.Failure(form, err)
    }
}