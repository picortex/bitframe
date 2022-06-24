package bitframe

import koncurrent.Fulfilled
import koncurrent.Later
import koncurrent.Settled
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.collections.interoperable.toInteroperableMutableList

fun <T, R> Iterable<Later<T>>.then(callback: (List<Settled<T>>) -> R): Later<R> {
    val later = Later<R>()
    val results = associateWith { it.state }.toMutableMap()
    for (lx in this) {
        if (lx.state !is Settled) lx.complete { state ->
            results[lx] = state
            if (results.values.all { it is Settled }) {
                later.resolveWith(callback(results.values.filterIsInstance<Settled<T>>().toInteroperableList()))
            }
        } else results[lx] = lx.state
    }
    return later
}

inline fun <T> List<Settled<T>>.filterFulfilledValues() = filterIsInstance<Fulfilled<T>>().map { it.value }.toInteroperableMutableList()

inline fun <T> List<Settled<T>>.firstFulfilledValue() = filterFulfilledValues().first()

inline fun <T> List<Settled<T>>.firstFulfilledOrNullValue() = filterFulfilledValues().firstOrNull()

fun <T, R> Later<Later<T>>.flatten(callback: (T) -> R): Later<R> = Later { resolve, reject ->
    then(
        onResolve = { innerLater ->
            innerLater.then(
                onResolve = { resolve(callback(it)) },
                onReject = { reject(it) }
            )
        },
        onReject = { reject(it) }
    )
}

inline fun <T> Later<Later<T>>.flatten(): Later<T> = flatten { it }