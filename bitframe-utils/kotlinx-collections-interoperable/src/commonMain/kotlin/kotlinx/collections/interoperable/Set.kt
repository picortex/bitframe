@file:JsExport

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.Set as KSet

@Serializable(with = ListSerializer::class)
abstract class Set<out E> : Collection<E>(), KSet<E>