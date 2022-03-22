package presenters.modal.builders

import presenters.actions.GenericAction

typealias FormDialogBuildingBlock<T> = FormDialogActionsBuilder<T>.() -> GenericAction<T>