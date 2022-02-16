package presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList

@JsExport
data class DropDownInputField(
    override val name: String,
    val options: List<Option>
) : InputField {
    @JsName("from")
    constructor(name: String, vararg options: Option) : this(name, options.toInteroperableList())

    val itemLabels get() = options.map { it.label }.toInteroperableList()
    val itemValues get() = options.map { it.value }.toInteroperableList()

    val selected get() = options.firstOrNull { it.selected }

    data class Option(
        val label: String,
        val value: String = label,
        val selected: Boolean = false
    )
}