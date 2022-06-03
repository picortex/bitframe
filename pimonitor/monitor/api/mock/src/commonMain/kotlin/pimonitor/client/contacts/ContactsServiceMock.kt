package pimonitor.client.contacts

import bitframe.client.ServiceConfigMock
import pimonitor.core.contacts.ContactsServiceDaod
import pimonitor.core.contacts.ContactsServiceCore

class ContactsServiceMock(
    override val config: ServiceConfigMock
) : ContactsService(config), ContactsServiceCore by ContactsServiceDaod(config)