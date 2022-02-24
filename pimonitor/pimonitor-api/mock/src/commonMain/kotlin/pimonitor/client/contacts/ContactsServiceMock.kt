package pimonitor.client.contacts

import bitframe.client.MockServiceConfig
import pimonitor.core.contacts.ContactsDaodService
import pimonitor.core.contacts.ContactsServiceCore

class ContactsServiceMock(
    override val config: MockServiceConfig
) : ContactsService(config), ContactsServiceCore by ContactsDaodService(config)