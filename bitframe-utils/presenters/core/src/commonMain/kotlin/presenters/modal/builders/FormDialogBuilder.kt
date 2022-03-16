package presenters.modal.builders

import presenters.modal.SubmitAction

class FormDialogBuilder<P> : DialogBuilder() {
    private var _submitAction: SubmitAction<P>? = null
    internal val submitAction: SubmitAction<P> get() = _submitAction ?: error("SubmitAction has not been initialize just yet")
    fun onSubmit(handler: (P) -> Unit): SubmitAction<P> {
        val action = SubmitAction("Submit", handler)
        _submitAction = action
        return action
    }
}