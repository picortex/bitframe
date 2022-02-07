@file:JsExport

package bitframe.service

import bitframe.service.config.ServiceConfig
import kotlinx.collections.interoperable.List
import later.Later
import kotlin.js.JsExport

abstract class GenericService<T>(open val config: ServiceConfig) {
    abstract fun create(input: T): Later<T>
    abstract fun update(obj: T): Later<T>
    abstract fun load(uid: String): Later<T>
    abstract fun loadOrNull(uid: String): Later<T?>
    abstract fun delete(uid: String): Later<T>
    abstract fun all(): Later<List<T>>
}