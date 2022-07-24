package presenters.collections

interface Table<T> : Paged<T>, Paginator<T> {
    val columns: Array<Column<T>>
    override fun map(paginator: Paginator<T>): Table<T>
}