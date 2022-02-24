@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.contacts

import bitframe.client.ServiceConfig
import bitframe.core.RequestBody
import bitframe.core.Session
import kotlinx.collections.interoperable.List
import later.Later
import later.await
import later.later
import pimonitor.core.contacts.ContactPersonSummary
import pimonitor.core.contacts.ContactsFilter
import pimonitor.core.contacts.ContactsServiceCore
import kotlin.js.JsExport

abstract class ContactsService(
    open val config: ServiceConfig
) : ContactsServiceCore {
    fun all(): Later<List<ContactPersonSummary>> = config.scope.later {
        val rb = RequestBody.Authorized(
            session = config.session.value as? Session.SignedIn ?: error("You have to be logged in to load all the available contacts in your space"),
            data = ContactsFilter("")
        )
        all(rb).await()
    }
}