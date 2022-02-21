@file:JsExport

package presenters.cards

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ValueCard(
    val title: String,
    val value: String,
    val details: String
)