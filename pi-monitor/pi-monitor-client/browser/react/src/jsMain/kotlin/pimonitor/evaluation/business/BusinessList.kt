package pimonitor.evaluation.business

import bitframe.presenters.collections.Table
import bitframe.presenters.collections.table.Row
import pimonitor.monitored.MonitoredBusiness
import pimonitor.reakt.ReactTable
import react.FC
import react.Props
import react.RBuilder
import react.functionComponent
import reakt.*
import useLive
import bitframe.presenters.collections.table.Column as TableColumn

fun <D> useTable(table: Table<D>) = useLive(table.live)

external interface BusinessTableProps : Props {
    var table: Table<MonitoredBusiness>
}

val BusinessTable = functionComponent<BusinessTableProps> { props ->
    val table = useTable(props.table)
    ReactTable(table)
}

internal fun RBuilder.BusinessTable(
    table: Table<MonitoredBusiness>
) = child(BusinessTable) {
    attrs.table = table
}