@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlin.js.JsExport

@JsExport
interface CreatorUpdater<in P, out R> : Creator<P, R>, Updater<IdentifiedRaw<P>, R>