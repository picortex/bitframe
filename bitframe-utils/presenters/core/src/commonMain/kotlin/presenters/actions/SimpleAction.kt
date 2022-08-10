@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.actions

import presenters.actions.internal.MutableSimpleActionImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

@JsExport
interface SimpleAction : Action<() -> Unit> {
    operator fun invoke()

    companion object {
        @JvmName("create")
        inline operator fun invoke(
            name: String, noinline handler: () -> Unit
        ): SimpleAction = MutableSimpleActionImpl(name, handler)
    }
}