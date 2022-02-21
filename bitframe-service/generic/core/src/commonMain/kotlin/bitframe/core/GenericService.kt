@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.core

import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport

interface GenericService<out T> {
    val config: ServiceConfig
    fun create(input: @UnsafeVariance T): Later<out T>
    fun update(obj: @UnsafeVariance T): Later<out T>
    fun load(uid: String): Later<out T>
    fun loadOrNull(uid: String): Later<out T?>
    fun delete(uid: String): Later<out T>
    fun all(): Later<out List<T>>
}