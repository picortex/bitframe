@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import kotlinx.serialization.StringFormat
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

@JsExport
interface AppScopeConfig<out A> : ViewModelConfig<A> {
    val codec: StringFormat
}