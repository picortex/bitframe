package bitframe.reakt

import bitframe.presenters.collections.Table
import bitframe.presenters.collections.table.Row
import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import live.Watcher
import react.*
import react.Component
import react.dom.attrs
import reakt.*
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.reflect.KClass
import bitframe.presenters.collections.table.Column as BColumn
import react.Props as RProps
import react.State as RState

external interface Props<D> : RProps {
    var table: Table<D>
}

data class State<D>(
    var table: Table.State<D>
) : RState

@JsExport
class BitframeTableComponent<D> private constructor(props: Props<D>) : RComponent<Props<D>, State<D>>(props) {
    private val table get() = props.table

    init {
        state = State(props.table.live.value)
    }

    private var watcher: Watcher<Table.State<D>>? = null

    override fun componentDidMount() {
        watcher = table.live.watch {
            setState { table = it }
        }
    }

    override fun componentWillUnmount() {
        watcher?.stop()
    }

    private fun BColumn<D>.toReactTableColumns(): Column<Row<D>> = when (val col = this) {
        is BColumn.Select -> RenderColumn(col.name) { row ->
            styledInput(type = InputType.checkBox) {
                attrs {
                    name = col.name
                    defaultChecked = row.selected
                    onClickFunction = { table.changeSelection(row) }
                }
            }
        }
        is BColumn.Data -> RenderColumn(col.name) { row ->
            styledDiv {
                css {
                    borderStyle = BorderStyle.solid
                    borderWidth = 3.px
                    borderColor = if (row.selected) Color.red else Color.transparent
                }
                +col.accessor(row)
            }
        }
        is BColumn.Action -> RenderColumn(col.name) { data ->
            Grid(cols = col.actions.joinToString(separator = " ") { "1fr" }) {
                col.actions.forEach { action ->
                    ContainedButton(action.name) { action.handler(data) }
                }
            }
        }
    }

    override fun RBuilder.render(): dynamic = ReactTable(
        state.table.rows,
        columns = state.table.columns.map { it.toReactTableColumns() }
    ).also {
        console.log("Rerendering")
    }
}

fun <D> RBuilder.BitframeTable(table: Table<D>) {
    child(BitframeTableComponent::class as KClass<out Component<Props<D>, *>>) {
        attrs.table = table
    }
}