package pimonitor.client.businesses

import events.Event
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams

class BusinessAddedEvent(
    data: CreateMonitoredBusinessParams,
    spaceId: String
) : Event<CreateMonitoredBusinessParams>(data, topic(spaceId)) {
    companion object {
        fun topic(spaceId: String) = "pimonitor.business.created[$spaceId]"
    }
}