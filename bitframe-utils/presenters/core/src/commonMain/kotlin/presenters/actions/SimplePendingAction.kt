@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.actions

import koncurrent.Later
import kotlin.js.JsExport

open class SimplePendingAction(
    override val name: String,
    override val handler: () -> Later<Unit>
) : Action<() -> Later<Unit>> {
    operator fun invoke() = handler()
    override fun hashCode() = name.hashCode()
    override fun toString() = "SimplePendingAction($name)"
    override fun equals(other: Any?) = other is SimplePendingAction && other.name == name
}