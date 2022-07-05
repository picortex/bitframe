@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.actions

import koncurrent.Later
import kotlin.js.JsExport

open class GenericPendingAction<in T>(
    override val name: String,
    override val handler: (T) -> Later<out Any?>
) : Action {
    operator fun invoke(arg: T) = handler(arg)
    override fun hashCode() = name.hashCode()
    override fun toString() = "GenericPendingAction($name)"
    override fun equals(other: Any?) = other is GenericPendingAction<*> && other.name == name
}