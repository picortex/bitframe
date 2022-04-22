@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.intents

import viewmodel.ViewModel
import kotlin.js.JsExport

class IndexIntents(private val viewmodel: ViewModel<IndexIntent, *>) {
    val load = { uid: String -> viewmodel.post(IndexIntent.Load(uid)) }
}