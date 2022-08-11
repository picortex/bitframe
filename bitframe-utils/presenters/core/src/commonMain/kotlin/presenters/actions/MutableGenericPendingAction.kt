@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.actions

import koncurrent.Later
import presenters.actions.internal.MutableGenericPendingActionImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

interface MutableGenericPendingAction<T> : GenericPendingAction<T>, MutableAction<(T) -> Later<out Any?>> {
    override var handler: (T) -> Later<out Any?>

    companion object {
        @JvmName("create")
        operator fun <T> invoke(
            name: String,
            handler: (T) -> Later<out Any>
        ): MutableGenericPendingAction<T> = MutableGenericPendingActionImpl(name, handler)
    }
}