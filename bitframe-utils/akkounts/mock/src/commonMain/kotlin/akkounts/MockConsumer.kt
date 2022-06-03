package akkounts

import akkounts.utils.unset
import kash.Currency
import kotlin.math.abs

data class MockConsumer(
    val uid: String = unset,
    val name: String,
    var currency: Currency = Currency.values[
            abs(uid.hashCode() + name.hashCode()) % Currency.values.size
    ]
)