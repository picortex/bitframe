package presenters.collections

import koncurrent.Later

typealias ChangePageHandler<T> = (current: Page<T>) -> Later<out Page<@UnsafeVariance T>>