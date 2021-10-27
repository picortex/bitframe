package pimonitor.evaluation.business

import bitframe.presenters.collections.Table
import pimonitor.monitored.MonitoredBusiness
import react.RBuilder
import reakt.*

internal fun RBuilder.BusinessTable(
    table: Table<MonitoredBusiness>
) = ReactTable(
    table.data,
    columns = listOf(
        Column("name") { it.name },
        Column("contact") { it.contacts.first().name },
        Column("email") { it.contacts.first().email.value },
        RenderColumn("actions") {
            Grid(cols = "1fr 1fr") {
                ContainedButton("View") {}
                ContainedButton("Test") {}
            }
        }
    )
)