@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "EqualsOrHashCode")

package presenters.modal

import presenters.actions.GenericAction
import presenters.actions.SimpleAction
import presenters.forms.FormActionsBuilder
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import kotlin.js.JsName

open class FormDialog<out F, in P>(
    override val heading: String,
    override val details: String,
    override val fields: F,
    override val actions: List<SimpleAction>,
    override val submit: GenericAction<P>
) : Dialog<F, P>, Form<F, P> {
    override val cancel by lazy {
        actions.firstOrNull {
            it.name.contentEquals("cancel", ignoreCase = true)
        } ?: error("No cancel action has been registered")
    }

    override val isForm = true
    override val isConfirm = false

    override val asForm get() = this

    @JsName("_ignore_fromBuildingBlock")
    constructor(heading: String, details: String, fields: F, block: FormActionsBuildingBlock<P>) : this(
        heading, details, fields,
        actions = FormActionsBuilder<P>().apply { block() }.actions,
        submit = FormActionsBuilder<P>().apply { block() }.submitAction
    )

    @JsName("fromForm")
    constructor(form: Form<F, P>) : this(
        heading = form.heading,
        details = form.details,
        fields = form.fields,
        actions = listOf(form.cancel),
        submit = form.submit,
    )

    override fun equals(other: Any?): Boolean = other is FormDialog<*, *> && other.heading == heading && other::class == this::class
}