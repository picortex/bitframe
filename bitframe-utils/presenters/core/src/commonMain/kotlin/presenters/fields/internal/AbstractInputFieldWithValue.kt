@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import live.mutableLiveOf
import presenters.fields.InputFieldFeedback
import presenters.fields.InputFieldWithValue
import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_READONLY
import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_REQUIRED
import kotlin.js.JsExport

abstract class AbstractInputFieldWithValue<T>(
    override val name: String,
    override val label: String = name,
    value: T,
    override val isReadonly: Boolean = DEFAULT_IS_READONLY,
    override val isRequired: Boolean = DEFAULT_IS_REQUIRED,
    override val validator: (T) -> T = { it }
) : InputFieldWithValue<T> {
    override val feedback = mutableLiveOf<InputFieldFeedback>(InputFieldFeedback.Empty)

    override var value: T = value
        set(value) {
            if (feedback.value != InputFieldFeedback.Empty) feedback.value = InputFieldFeedback.Empty
            field = value
        }

    override fun validate() {
        try {
            validator(value)
            feedback.value = InputFieldFeedback.Valid
        } catch (err: Throwable) {
            feedback.value = InputFieldFeedback.Error(err.message ?: "Invalid input $value for field $name")
        }
    }

    val asteriskedLabel get() = labelWithAsterisks

    val labelWithAsterisks get() = (if (isRequired) "*" else "") + label
}