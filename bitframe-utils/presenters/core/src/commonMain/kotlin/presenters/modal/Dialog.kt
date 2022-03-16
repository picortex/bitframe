@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import kotlinx.collections.interoperable.List
import presenters.modal.builders.ConfirmDialogBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.modal.builders.FormDialogBuilder
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport
import kotlin.js.JsName

sealed class Dialog {
    abstract val heading: String
    abstract val details: String
    abstract val actions: List<DialogAction>

    open class Form<out F, in P>(
        override val heading: String,
        override val details: String,
        val fields: F,
        override val actions: List<DialogAction>,
        val submit: SubmitAction<P>
    ) : Dialog() {
        @JsName("_ignore_fromBuildingBlock")
        constructor(heading: String, details: String, fields: F, block: FormDialogBuildingBlock<P>) : this(
            heading, details, fields,
            actions = FormDialogBuilder<P>().apply { block() }.actions,
            submit = FormDialogBuilder<P>().apply { block() }.submitAction
        )
    }

    open class Confirm(
        override val heading: String,
        override val details: String,
        override val actions: List<DialogAction>,
        val confirm: ConfirmAction
    ) : Dialog() {
        @JsName("_ignore_fromBuildingBlock")
        constructor(heading: String, details: String, block: ConfirmDialogBuildingBlock) : this(
            heading, details,
            actions = ConfirmDialogBuilder().apply { block() }.actions,
            confirm = ConfirmDialogBuilder().apply { block() }.confirmAction
        )
    }

    override fun equals(other: Any?): Boolean = other is Dialog && other.heading == heading && other::class == this::class
}