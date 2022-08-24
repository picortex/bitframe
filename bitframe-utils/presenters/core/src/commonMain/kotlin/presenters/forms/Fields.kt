@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import presenters.fields.InputField
import presenters.fields.InputFieldState
import presenters.fields.ValuedField
import kotlin.js.JsExport

open class Fields(internal val cache: MutableMap<String, InputField> = mutableMapOf()) {

    internal val all get() = cache.values

//    @JsName("_ignore_are_valid")
//    val areValid get() = valuesToBeSubmitted.all { it.feedback.value !is InputFieldState.Error }
//
//    @JsName("_ignore_are_not_valid")
//    val areNotValid get() = valuesToBeSubmitted.any { it.feedback.value is InputFieldState.Error }

    internal val valuesInJson
        get() = valuesToBeSubmitted.associate {
            it.name to it.value
        }.map { (key, value) -> key to value }.joinToString(prefix = "{", postfix = "}") { (key, value) ->
            """"$key": ${if (value != null) """"$value"""" else null}"""
        }

    internal val allInvalid get() = valuesToBeSubmitted.filter { it.feedback.value is InputFieldState.Error }

    private val valueFields get() = cache.values.filterIsInstance<ValuedField<*>>()

    private val valuesToBeSubmitted
        get() = valueFields.filterNot {
            !it.isRequired && (it.value == null || it.value.toString().isBlank())
        }

    fun validate() {
        valuesToBeSubmitted.forEach { it.validateWithFeedback() }
    }

    fun clearAll() {
        valueFields.forEach {
            it.value = null
            it.feedback.value = InputFieldState.Empty
        }
    }
}