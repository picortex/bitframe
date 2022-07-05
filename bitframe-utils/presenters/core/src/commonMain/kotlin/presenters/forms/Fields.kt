@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.forms

import kotlinx.collections.interoperable.toInteroperableList
import presenters.fields.InputField
import presenters.fields.InputFieldFeedback
import presenters.fields.InputFieldWithValue
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.reflect.KProperty

open class Fields(internal val cache: MutableMap<KProperty<*>, InputField> = mutableMapOf()) {

    @JsName("_ignore_all")
    val all get() = cache.values.toInteroperableList()

    @JsName("_ignore_are_valid")
    val areValid get() = valueFields.all { it.feedback.value !is InputFieldFeedback.Error }

    @JsName("_ignore_are_not_valid")
    val areNotValid get() = valueFields.any { it.feedback.value is InputFieldFeedback.Error }

    internal val values
        get() = valueFields.associate {
            it.name to it.value
        }

    private val valueFields get() = cache.values.filterIsInstance<InputFieldWithValue<*>>()

    fun validate() {
        valueFields.forEach { it.validate() }
    }
}