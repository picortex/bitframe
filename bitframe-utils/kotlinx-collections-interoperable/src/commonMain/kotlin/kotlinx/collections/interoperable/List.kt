package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.collections.List as KList

@Serializable(with = ListSerializer::class)
expect abstract class List<out E>() : KList<E>