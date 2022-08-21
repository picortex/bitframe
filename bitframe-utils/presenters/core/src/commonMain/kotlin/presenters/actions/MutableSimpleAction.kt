@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.actions

import presenters.actions.internal.MutableSimpleActionImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName

@JsExport
interface MutableSimpleAction : SimpleAction, MutableAction<() -> Unit> {
    override var handler: () -> Unit

    companion object {
        @JvmName("create")
        inline operator fun invoke(
            name: String,
            noinline handler: () -> Unit
        ): MutableSimpleAction = MutableSimpleActionImpl(name, handler)
    }
}