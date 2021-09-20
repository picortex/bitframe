package pimonitor.evaluation.business

import pimonitor.Monitor
import react.RBuilder
import reakt.*

fun RBuilder.BusinessList(
    data: List<Monitor.Business>
) = ReactTable(
    data,
    columns = listOf(
        Column("name") { it.name },
        Column("email") { it.email.value },
        Column("contact") {  },
        RenderColumn("actions") {
            Grid(cols = "1fr 1fr") {
                ContainedButton("View") {}
                ContainedButton("Test") {}
            }
        }
    )
)