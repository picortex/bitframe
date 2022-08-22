@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.actions

import koncurrent.Later
import presenters.actions.internal.MutableGenericActionImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

interface GenericAction<in T> : Action<(T) -> Later<Any?>> {
    operator fun invoke(arg: T): Later<Any?>

    companion object {
        @JvmName("create")
        inline operator fun <T> invoke(
            name: String,
            noinline handler: (T) -> Later<Any?>
        ): GenericAction<T> = MutableGenericActionImpl(name, handler)

        @JvmName("create")
        @JsName("createLater")
        inline operator fun <T> invoke(
            name: String,
            noinline handler: (T) -> Any?
        ): GenericAction<T> = MutableGenericActionImpl(name) { res ->
            Later.resolve(res).then { handler(it) }
        }
    }
}