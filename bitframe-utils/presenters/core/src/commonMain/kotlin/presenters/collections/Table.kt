package presenters.collections

interface Table<T> :
    Pageable<T>, Paginator<T>,
    Selectable<T>, Selector<T>,
    Actionable, ActionManager {
    val columns: Array<Column<T>>
    override fun map(paginator: Paginator<T>): Table<T>
}