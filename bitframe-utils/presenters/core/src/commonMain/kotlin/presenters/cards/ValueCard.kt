@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.cards

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ValueCard<T>(
    val title: String,
    val value: T,
    val details: String
)