@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.scopes

import kotlinx.collections.interoperable.List
import presenters.actions.SimpleAction
import presenters.actions.SimpleActionsBuilder
import presenters.table.Table
import kotlin.js.JsExport
import kotlin.js.JsName
import presenters.cases.Failure as FailureCase
import presenters.cases.Loading as LoadingCase
import presenters.cases.Success as SuccessCase

sealed class DetailState<out T> {

}