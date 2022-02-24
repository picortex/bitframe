package pimonitor.core.contacts

import bitframe.core.*
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.toMonitoredBusinessRef

open class ContactsDaodService(
    open val config: DaodServiceConfig
) : ContactsServiceCore, LoadContactsUseCase by LoadContactsUseCaseImpl(config)