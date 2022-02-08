@file:JsExport

package bitframe.service

import bitframe.service.config.ServiceConfig
import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport

abstract class GenericService<out T>(open val config: ServiceConfig) {
    abstract fun create(input: @UnsafeVariance T): Later<out T>
    abstract fun update(obj: @UnsafeVariance T): Later<out T>
    abstract fun load(uid: String): Later<out T>
    abstract fun loadOrNull(uid: String): Later<out T?>
    abstract fun delete(uid: String): Later<out T>
    abstract fun all(): Later<out List<T>>
}