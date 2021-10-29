package pimonitor.evaluation.business

import bitframe.presenters.collections.Table
import pimonitor.monitored.MonitoredBusiness
import pimonitor.reakt.BitframeTable
import react.Props
import react.RBuilder
import react.functionComponent
import useLive

fun <D> useTable(table: Table<D>) = useLive(table.live)

external interface BusinessTableProps : Props {
    var table: Table<MonitoredBusiness>
}

val BusinessTable = functionComponent<BusinessTableProps> { props ->
    BitframeTable(props.table)
//    ReactTable(props.table)
}

internal fun RBuilder.BusinessTable(
    table: Table<MonitoredBusiness>
) = child(BusinessTable) {
    attrs.table = table
}