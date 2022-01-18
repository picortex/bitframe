package pimonitor.evaluation.businesses

import presenters.collections.Table
import pimonitor.monitored.MonitoredBusiness
import pimonitor.reakt.BitframeTable
import react.*
import useLive

fun <D> useTable(table: Table<D>) = useLive(table.live)

external interface BusinessTableProps : Props {
    var table: Table<MonitoredBusiness>
}

val BusinessTable = fc<BusinessTableProps> { props ->
    BitframeTable(props.table)
//    ReactTable(props.table)
}

internal fun RBuilder.BusinessTable(
    table: Table<MonitoredBusiness>
) = child(BusinessTable) {
    attrs.table = table
}