@file:JsExport

package presenters.modal

import kotlin.js.JsExport

sealed class ConfirmDialogState {
    object Pending : ConfirmDialogState()
    object Executing : ConfirmDialogState()
    sealed class Executed : ConfirmDialogState() {
        object Successfully : ConfirmDialogState()
        object Exceptionally : ConfirmDialogState()
    }
}
