package pimonitor.core.contacts

import bitframe.core.*

open class ContactsServiceDaod(
    open val config: ServiceConfigDaod
) : ContactsServiceCore, LoadContactsUseCase by LoadContactsUseCaseImpl(config)