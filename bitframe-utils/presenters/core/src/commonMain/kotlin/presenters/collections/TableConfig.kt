package presenters.collections

interface TableConfig<D> : PageableConfig {
    val columns: Array<Column<D>>
}