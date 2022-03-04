@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

@JsExport
interface ReactUIScope<in I, S> : UIScope<I, S> {
    val useScopeState: () -> S
}