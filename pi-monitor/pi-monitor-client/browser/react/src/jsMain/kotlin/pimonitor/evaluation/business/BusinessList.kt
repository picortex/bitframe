package pimonitor.evaluation.business

import pimonitor.monitored.MonitoredBusiness
import react.RBuilder
import reakt.*

internal fun RBuilder.BusinessList(
    data: List<MonitoredBusiness>
) = ReactTable(
    data,
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