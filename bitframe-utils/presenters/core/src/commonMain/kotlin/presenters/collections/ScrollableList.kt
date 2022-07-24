package presenters.collections

interface ScrollableList<out T> : Pageable<T> {
    override fun map(paginator: Paginator<@UnsafeVariance T>): ScrollableList<T>
}