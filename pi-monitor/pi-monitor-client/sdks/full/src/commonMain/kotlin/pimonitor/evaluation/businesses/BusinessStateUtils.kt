package pimonitor.evaluation.businesses

import bitframe.presenters.collections.tableOf
import pimonitor.monitored.MonitoredBusiness

internal fun businessTable(businesses: List<MonitoredBusiness>) = tableOf(businesses) {
    selectable()
    column("Name") { it.data.name }
    column("Contact Name") { it.data.contacts.first().name }
    column("Contact Email") { it.data.contacts.first().email.value }
    actions("Actions") {
        action("Delete") { }
        action("View") { }
    }
}