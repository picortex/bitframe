@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

@JsExport
interface CRUD<in P, out R> : CreatorUpdater<P, R>,
    Deleter<Array<out String>, List<R>>,
    Reader<String, R>,
    Lister<Any?, R>