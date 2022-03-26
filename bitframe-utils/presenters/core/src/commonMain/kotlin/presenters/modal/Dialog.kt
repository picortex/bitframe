@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import kotlinx.collections.interoperable.List
import presenters.actions.GenericAction
import presenters.actions.SimpleAction
import presenters.modal.builders.ConfirmDialogActionsBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.modal.builders.FormDialogActionsBuilder
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport
import kotlin.js.JsName

sealed class Dialog<out F, in P> {
    abstract val heading: String
    abstract val details: String
    abstract val actions: List<SimpleAction>

    open class Form<out F, in P>(
        override val heading: String,
        override val details: String,
        override val fields: F,
        override val actions: List<SimpleAction>,
        override val submit: GenericAction<P>
    ) : Dialog<F, P>(), presenters.forms.Form<F, P> {
        override val cancel by lazy {
            actions.firstOrNull {
                it.name.contentEquals("cancel", ignoreCase = true)
            } ?: error("No cancel action has been registered")
        }

        @JsName("_ignore_fromBuildingBlock")
        constructor(heading: String, details: String, fields: F, block: FormDialogBuildingBlock<P>) : this(
            heading, details, fields,
            actions = FormDialogActionsBuilder<P>().apply { block() }.actions,
            submit = FormDialogActionsBuilder<P>().apply { block() }.submitAction
        )
    }

    open class Confirm(
        override val heading: String,
        override val details: String,
        override val actions: List<SimpleAction>,
        val confirm: SimpleAction
    ) : Dialog<Nothing, Nothing>() {
        @JsName("_ignore_fromBuildingBlock")
        constructor(heading: String, details: String, block: ConfirmDialogBuildingBlock) : this(
            heading, details,
            actions = ConfirmDialogActionsBuilder().apply { block() }.actions,
            confirm = ConfirmDialogActionsBuilder().apply { block() }.confirmAction
        )
    }

    override fun equals(other: Any?): Boolean = other is Dialog<*, *> && other.heading == heading && other::class == this::class
}