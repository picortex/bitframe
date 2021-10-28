package pimonitor.reakt

import bitframe.presenters.collections.Table
import bitframe.presenters.collections.table.Column
import bitframe.presenters.collections.table.Row
import kotlinx.html.InputType
import react.RBuilder
import reakt.*
import styled.styledInput

fun <D> RBuilder.ReactTable(table: Table.State<D>) = ReactTable(
    table.data,
    columns = table.columns.map { col ->
        when (col) {
            is Column.Select -> RenderColumn(col.name) {
                styledInput(type = InputType.checkBox) {
                    attrs.name = col.name
                }
            }
            is Column.Data -> Column(col.name) {
                col.accessor(Row(0, it))
            }
            is Column.Action -> RenderColumn(col.name) { data ->
                Grid(cols = col.actions.joinToString(separator = " ") { "1fr" }) {
                    col.actions.forEach { action ->
                        ContainedButton(action.name) { action.handler(data) }
                    }
                }
            }
        }
    }
)