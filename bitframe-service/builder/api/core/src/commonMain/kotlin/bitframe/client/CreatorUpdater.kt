@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.IdentifiedRaw
import kotlin.js.JsExport

@JsExport
interface CreatorUpdater<in P, out R> : Creator<P, R>, Updater<IdentifiedRaw<P>, R>