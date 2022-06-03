package pimonitor.client.businesses

import events.Event
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult

class BusinessAddedEvent(
    data: CreateMonitoredBusinessResult,
    spaceId: String
) : Event<CreateMonitoredBusinessResult>(data, topic(spaceId)) {
    companion object {
        fun topic(spaceId: String) = "pimonitor.business.created[$spaceId]"
    }
}