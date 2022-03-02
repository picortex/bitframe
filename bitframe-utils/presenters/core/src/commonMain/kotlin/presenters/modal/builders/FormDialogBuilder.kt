package presenters.modal.builders

import presenters.modal.SubmitAction

class FormDialogBuilder<P> : DialogBuilder() {
    internal var submitAction: SubmitAction<P>? = null
    fun onSubmit(handler: (P) -> Unit): SubmitAction<P> {
        val action = SubmitAction("Submit", handler)
        submitAction = action
        return action
    }
}