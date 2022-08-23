@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import koncurrent.Fulfilled
import koncurrent.Later
import koncurrent.Rejected
import koncurrent.later.catch
import koncurrent.later.finally
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import presenters.actions.GenericAction
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

    override val submit: GenericAction<P> = builtActions.submitAction

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
    fun submit() = try {
        ui.value = FormState.Submitting
        validate()
        val values = fields.valuesInJson
        logger.obj(values)
        log("After printing values")
        if (fields.areNotValid) throw IllegalArgumentException("Some values are invalid")
        log("Before invoke")
        submit.invoke(codec.decodeFromString(config.serializer, values)).finally {
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
        Later.reject(err, config.executor)
    }
//
//    @JsName("send")
//    fun submit() = Later.resolve(Unit, executor).then {
//        ui.value = FormState.Submitting
//        log("Before validation")
//        validate()
//        log("After validation")
//        if (fields.areNotValid) throw IllegalArgumentException("Some values are invalid")
//        log("Json values")
//        val values = fields.valuesInJson
//        logger.obj(values)
//        values
//    }.andThen { values ->
//        submit.invoke(codec.decodeFromString(config.serializer, values)).finally {
//            val (state, action) = when (it) {
//                is Fulfilled -> FormState.Submitted to afterSubmitAction
//                is Rejected -> FormState.Failure(it.cause) to failureAction
//            }
//            ui.value = state
//            action?.invoke(fields)
//            if (config.exitOnSubmitted) cancel()
//        }
//    }.catch { err ->
//        ui.value = FormState.Failure(err)
//        failureAction?.invoke(fields)
//        Later.reject(err, config.executor)
//    }
}