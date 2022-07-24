package presenters.collections

interface TableConfig<D> : PagedConfig {
    val columns: Array<Column<D>>
}