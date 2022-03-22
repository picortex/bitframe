@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.actions

import kotlin.js.JsExport

open class GenericAction<in T>(
    override val name: String,
    override val handler: (T) -> Unit
) : Action {
    operator fun invoke(arg: T) = handler(arg)
    override fun hashCode() = name.hashCode()
    override fun toString() = "GenericAction($name)"
    override fun equals(other: Any?) = other is GenericAction<*> && other.name == name
}