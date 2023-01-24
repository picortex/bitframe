@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kollections.List
import kotlin.js.JsExport

@JsExport
interface AuthorizedCRUD<in P, out R> :
    AuthorizedCreator<P, R>,
    AuthorizedUpdater<IdentifiedRaw<P>, R>,
    AuthorizedDeleter<Array<out String>, List<R>>,
    AuthorizedReader<String, R>,
    AuthorizedLister<Any?, R>