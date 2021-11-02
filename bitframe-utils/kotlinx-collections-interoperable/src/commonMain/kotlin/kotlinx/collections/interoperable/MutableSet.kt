@file:JsExport

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.MutableSet as KMutableSet

@Serializable(with = ListSerializer::class)
abstract class MutableSet<E> : Collection<E>(), KMutableSet<E>