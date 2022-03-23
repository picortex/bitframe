@file:JsExport

package presenters.actions

import kotlin.js.JsExport

open class SimpleAction(
    override val name: String,
    override val handler: () -> Unit
) : Action {
    operator fun invoke() = handler()
    override fun hashCode() = name.hashCode()
    override fun toString() = "SimpleAction($name)"
    override fun equals(other: Any?) = other is SimpleAction && other.name == name
}