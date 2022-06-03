@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.contacts

import pimonitor.core.search.SearchResult
import presenters.cases.Feedback
import presenters.table.Table
import presenters.table.builders.tableOf
import kotlin.js.JsExport

data class ContactsState(
    val status: Feedback = Feedback.Loading("Loading contacts, please wait. . ."),
    val table: Table<SearchResult.ContactPersonSummary> = tableOf(emptyList()) {}
)
