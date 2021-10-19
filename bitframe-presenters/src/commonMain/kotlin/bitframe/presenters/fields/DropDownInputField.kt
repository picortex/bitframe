@file:JsExport

package bitframe.presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName

data class DropDownInputField(
    @JsName("_options") val options: List<Option>
) {
    @JsName("from")
    constructor(vararg options: Option) : this(options.toList())

    val items get() = options.toTypedArray()

    val selected get() = options.firstOrNull { it.selected }

    data class Option(
        val label: String,
        val value: String = label,
        val selected: Boolean = false
    )
}