@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import live.mutableLiveOf
import presenters.fields.InputFieldFeedback
import presenters.fields.TextInputFieldRaw
import kotlin.js.JsExport

abstract class AbstractTextInputFieldRaw(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override var value: String? = null,
    override val isReadonly: Boolean = false,
    override val validator: (String?) -> String? = { it }
) : TextInputFieldRaw {
    override val feedback = mutableLiveOf<InputFieldFeedback>(InputFieldFeedback.Empty)

    override fun validate() {
        try {
            validator(value)
            feedback.value = InputFieldFeedback.Valid
        } catch (err: Throwable) {
            feedback.value = InputFieldFeedback.Error(err.message ?: "Invalid input $value for field $name")
        }
    }
}