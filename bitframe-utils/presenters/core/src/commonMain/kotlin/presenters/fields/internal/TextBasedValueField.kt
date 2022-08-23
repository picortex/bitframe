@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.fields.internal

import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_READONLY
import presenters.fields.InputFieldWithValue.Companion.DEFAULT_IS_REQUIRED
import presenters.fields.ValuedField.Companion.DEFAULT_VALIDATOR
import presenters.fields.ValuedField.Companion.DEFAULT_VALUE
import kotlin.js.JsExport

abstract class TextBasedValueField(
    override val name: String,
    override val label: String = name,
    override val hint: String = label,
    override val defaultValue: String? = DEFAULT_VALUE,
    override val isReadonly: Boolean = DEFAULT_IS_READONLY,
    override val isRequired: Boolean = DEFAULT_IS_REQUIRED,
    open val maxLength: Int? = DEFAULT_MAX_LENGTH,
    open val minLength: Int? = DEFAULT_MIN_LENGTH,
    override val validator: ((String?) -> Unit)? = DEFAULT_VALIDATOR
) : AbstractValuedField<String>(name, label, hint, defaultValue, isReadonly, isRequired, validator) {
    companion object {
        val DEFAULT_MAX_LENGTH: Int? = null
        val DEFAULT_MIN_LENGTH: Int? = null
    }

    override fun validate(value: String?) {
        if (isRequired && value.isNullOrBlank()) {
            throw IllegalArgumentException("${label.replaceFirstChar { it.uppercase() }} is required")
        }
        val max = maxLength
        if (max != null && value != null && value.length > max) {
            throw IllegalArgumentException("${label.replaceFirstChar { it.uppercase() }} should not contain more than $max characters")
        }
        val min = minLength
        if (min != null && value != null && value.length < min) {
            throw IllegalArgumentException("${label.replaceFirstChar { it.uppercase() }} should not contain less than $min characters")
        }
        validator?.invoke(value)
    }
}