package presenters.collections

import koncurrent.Later

class PaginatorBuilder<T> {
    fun onNext(handler: (current: Page<T>) -> Later<out Page<T>>) {

    }
}