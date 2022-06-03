@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlin.js.JsExport

@JsExport
interface AuthorizedCreatorUpdater<in P, out R> :
    AuthorizedCreator<P, R>,
    AuthorizedUpdater<IdentifiedRaw<P>, R>