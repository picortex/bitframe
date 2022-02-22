package pimonitor.client.contacts

import bitframe.client.KtorServiceConfig
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.contacts.ContactPersonSummary
import pimonitor.core.contacts.ContactsFilter

class ContactsServiceKtor(
    override val config: KtorServiceConfig
) : ContactsService(config) {
    override fun all(rb: RequestBody.Authorized<ContactsFilter>): Later<List<ContactPersonSummary>> {
        TODO("Not yet implemented")
    }
}