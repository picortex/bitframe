@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.actions

import koncurrent.Later
import presenters.actions.internal.MutableGenericPendingActionImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

interface GenericPendingAction<in T> : Action<(T) -> Later<out Any?>> {
    operator fun invoke(arg: T): Later<out Any?>

    companion object {
        @JvmName("create")
        operator fun <T> invoke(
            name: String,
            handler: (T) -> Later<out Any?>
        ): GenericPendingAction<T> = MutableGenericPendingActionImpl(name, handler)
    }
}