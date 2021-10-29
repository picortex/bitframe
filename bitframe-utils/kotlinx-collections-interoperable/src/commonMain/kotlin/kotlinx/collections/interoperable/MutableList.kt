@file:JsExport

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.MutableListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.MutableList as KMutableList

@Serializable(with = MutableListSerializer::class)
abstract class MutableList<E> : List<E>(), KMutableList<E>