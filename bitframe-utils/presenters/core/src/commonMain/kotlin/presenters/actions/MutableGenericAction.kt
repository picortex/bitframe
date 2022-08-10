@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.actions

import presenters.actions.internal.MutableGenericActionImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

interface MutableGenericAction<T> : GenericAction<T>, MutableAction<(T) -> Unit> {
    companion object {
        @JvmName("create")
        inline operator fun <T> invoke(
            name: String,
            noinline handler: (T) -> Unit
        ): MutableGenericAction<T> = MutableGenericActionImpl(name, handler)
    }
}