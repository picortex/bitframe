@file:JsExport

package presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList

data class DropDownInputField(
    override val name: String,
    val label: String = name,
    val options: List<Option>
) : InputField {
    @JsName("from")
    constructor(name: String, label: String = name, vararg options: Option) : this(name, label, options.toInteroperableList())

    val itemLabels get() = options.map { it.label }.toInteroperableList()
    val itemValues get() = options.map { it.value }.toInteroperableList()

    val selected get() = options.firstOrNull { it.selected }

    data class Option(
        val label: String,
        val value: String = label,
        val selected: Boolean = false
    )
}