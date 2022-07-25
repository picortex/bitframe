package presenters.collections

interface ScrollableList<T> :
    Pageable<T>, Paginator<T>,
    Selectable<T>, Selector<T>,
    Actionable, ActionManager {
}