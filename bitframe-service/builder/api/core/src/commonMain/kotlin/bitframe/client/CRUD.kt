@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.IdentifiedRaw
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

@JsExport
interface CRUD<P, R> : Creator<P, R>, Reader<String,R>,Updater<IdentifiedRaw<P>, R>, Deleter<Array<out String>, List<R>>, Lister<Any?, R>