package presenters.modal.builders

import presenters.modal.SubmitAction

typealias FormDialogBuildingBlock<T> = FormDialogBuilder<T>.() -> SubmitAction<T>