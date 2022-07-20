@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Fulfilled
import koncurrent.Rejected
import koncurrent.later.finally
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import presenters.actions.GenericPendingAction
import presenters.actions.SimpleAction
import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

open class Form<out F : Fields, in P>(
    override val heading: String,
    override val details: String,
    override val fields: F,
    private val config: FormConfig<P>,
    initializer: FormActionsBuildingBlock<P>,
) : ViewModel<FormState>(config.of(FormState.Fillable)), BaseForm<F, P> {

    private val builtActions = FormActionsBuilder<P>().apply { initializer() }

    override val cancel = builtActions.actions.firstOrNull {
        it.name.contentEquals("Cancel", ignoreCase = true)
    } ?: SimpleAction("Cancel") {}

    override val submit: GenericPendingAction<P> = builtActions.submitAction

    private val codec get() = config.codec

    @JsName("handleCancel")
    fun cancel() {
        try {
            cancel.invoke()
        } catch (err: Throwable) {
            ui.value = FormState.Failure(err)
        }
    }

    fun exit() = cancel()

    override fun validate() = fields.validate()

    private var afterSubmitAction: ((F) -> Unit)? = null
    private var failureAction: ((F) -> Unit)? = null

    fun onSubmitted(handler: (F) -> Unit) {
        afterSubmitAction = handler
    }

    fun onFailure(handler: (F) -> Unit) {
        failureAction = handler
    }

    @JsName("send")
    fun submit() {
        try {
            ui.value = FormState.Submitting
            validate()
            if (fields.areNotValid) error("Some values are invalid")
            val encoded = codec.encodeToString(
                serializer = MapSerializer(String.serializer(), String.serializer()),
                value = fields.values as Map<String, String>
            )
            submit.invoke(codec.decodeFromString(config.serializer, encoded)).finally {
                val (state, action) = when (it) {
                    is Fulfilled -> FormState.Submitted to afterSubmitAction
                    is Rejected -> FormState.Failure(it.cause) to failureAction
                }
                ui.value = state
                action?.invoke(fields)
                if (config.exitOnSubmitted) cancel()
            }
        } catch (err: Throwable) {
            ui.value = FormState.Failure(err)
            failureAction?.invoke(fields)
        }
    }
}