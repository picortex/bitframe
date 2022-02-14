@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

interface ReactUIScope<in I, S> : UIScope<I, S> {
    val useScopeState: () -> S
}