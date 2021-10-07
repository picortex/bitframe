@file:JsExport

package bitframe.presenters.fields

import kotlin.js.JsExport
import kotlin.js.JsName

data class DropDownInputField(
    val options: List<Option>
) {
    @JsName("from")
    constructor(vararg options: Option) : this(options.toList())

    val items get() = options.toTypedArray()

    data class Option(
        val label: String, val value: String = label, val selected: Boolean = false
    )
}