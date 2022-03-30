@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.table

import presenters.actions.GenericAction
import kotlin.js.JsExport

typealias RowAction<D> = GenericAction<Row<D>>