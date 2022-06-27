@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import viewmodel.ViewModel
import kotlin.js.JsExport

data class Scope<I : ViewModel<S>, S>(val intents: I, val state: S)