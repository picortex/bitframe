@file:JsExport

package pimonitor.evaluation.contacts

import presenters.collections.Table
import kotlin.js.JsExport

sealed class ContactsState {
    data class Loading(val message: String) : ContactsState()

    //    data class Contacts(val table: Table<ContactModel>) : ContactsState()
    data class Failure(val cause: Throwable, val message: String? = cause.message) : ContactsState()
}
