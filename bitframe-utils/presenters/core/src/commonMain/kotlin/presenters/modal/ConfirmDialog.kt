@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "EqualsOrHashCode")

package presenters.modal

import presenters.actions.SimpleAction
import presenters.modal.builders.ConfirmDialogActionsBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport
import kotlin.js.JsName

open class ConfirmDialog(
    override val heading: String,
    override val details: String,
    override val actions: List<SimpleAction>,
    val confirm: SimpleAction
) : Dialog<Nothing, Nothing> {
    override val isForm = false
    override val isConfirm = true
    
    override val asConfirm get() = this

    @JsName("_ignore_fromBuildingBlock")
    constructor(heading: String, details: String, block: ConfirmDialogBuildingBlock) : this(
        heading, details,
        actions = ConfirmDialogActionsBuilder().apply { block() }.actions,
        confirm = ConfirmDialogActionsBuilder().apply { block() }.confirmAction
    )

    override fun equals(other: Any?): Boolean = other is ConfirmDialog && other.heading == heading && other::class == this::class
}