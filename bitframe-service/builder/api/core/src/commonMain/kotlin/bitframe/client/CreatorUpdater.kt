@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.IdentifiedRaw
import later.Later
import kotlin.js.JsExport

@JsExport
interface CreatorUpdater<P, R> : Creator<P, R>, Updater<IdentifiedRaw<P>, R>