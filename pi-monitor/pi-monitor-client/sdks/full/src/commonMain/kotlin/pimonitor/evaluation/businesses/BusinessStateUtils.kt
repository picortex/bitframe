package pimonitor.evaluation.businesses

import bitframe.presenters.collections.tableOf
import pimonitor.monitored.MonitoredBusiness

internal fun businessTable(businesses: List<MonitoredBusiness>) = tableOf(businesses) {
    selectable()
    column("Name") { it.data.name }
    column("Reporting") { "None" }
    column("Revenue") { "None" }
    column("Expenses") { "None" }
    column("GP") { "None" }
    column("Velocity") { "2m/s" }
    column("NCF") { "None" }
    actions("Actions") {
        action("Delete") { }
        action("View") { }
    }
}