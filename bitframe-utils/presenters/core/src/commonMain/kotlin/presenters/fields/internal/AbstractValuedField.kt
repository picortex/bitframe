@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import live.mutableLiveOf
import presenters.fields.InputFieldState
import presenters.fields.ValuedField
import presenters.fields.ValuedField.Companion.DEFAULT_IS_READONLY
import presenters.fields.ValuedField.Companion.DEFAULT_IS_REQUIRED
import presenters.fields.ValuedField.Companion.DEFAULT_VALIDATOR
import presenters.fields.ValuedField.Companion.DEFAULT_VALUE
import kotlin.js.JsExport

abstract class AbstractValuedField<T : Any>(
    override val name: String,
    override val label: String = name,
    open val hint: String = label,
    override val defaultValue: T? = DEFAULT_VALUE,
    override val isReadonly: Boolean = DEFAULT_IS_READONLY,
    override val isRequired: Boolean = DEFAULT_IS_REQUIRED,
    override val validator: ((T) -> Unit)? = DEFAULT_VALIDATOR
) : ValuedField<T> {
    override val feedback = mutableLiveOf<InputFieldState>(InputFieldState.Empty)

    override var value: T? = null
        set(value) {
            update(value)
            field = value
        }

    private fun update(value: T?) {
        try {
            validate(value)
            if (feedback.value != InputFieldState.Empty) {
                feedback.value = InputFieldState.Empty
            }
        } catch (err: Throwable) {
            feedback.value = InputFieldState.Warning(err.message ?: "", err)
        }
    }

    abstract override fun validate(value: T?)

    override fun validateWithFeedback(value: T?) {
        try {
            validate(value)
            if (feedback.value != InputFieldState.Empty) {
                feedback.value = InputFieldState.Empty
            }
        } catch (err: Throwable) {
            feedback.value = InputFieldState.Error(err.message ?: "", err)
        }
    }
}