@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.actions

import presenters.actions.internal.MutableGenericActionImpl
import kotlin.js.JsExport

interface GenericAction<in T> : Action<(T) -> Unit> {
    operator fun invoke(arg: T)

    companion object {
        operator fun <T> invoke(
            name: String,
            handler: (T) -> Unit
        ): GenericAction<T> = MutableGenericActionImpl(name, handler)
    }
}