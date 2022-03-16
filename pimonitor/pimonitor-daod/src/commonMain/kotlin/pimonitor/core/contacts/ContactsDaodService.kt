package pimonitor.core.contacts

import bitframe.core.*

open class ContactsDaodService(
    open val config: ServiceConfigDaod
) : ContactsServiceCore, LoadContactsUseCase by LoadContactsUseCaseImpl(config)