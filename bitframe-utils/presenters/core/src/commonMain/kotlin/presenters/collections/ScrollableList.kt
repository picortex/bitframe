package presenters.collections

interface ScrollableList<out T> : Paged<T> {
    override fun map(paginator: Paginator<@UnsafeVariance T>): ScrollableList<T>
}