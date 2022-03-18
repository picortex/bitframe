@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

@JsExport
interface ReactUIScope<out S> : UIScope<S> {
    val useScopeState: () -> S
}