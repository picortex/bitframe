package akkounts

import akkounts.reports.utils.CategoryEntry
import akkounts.reports.utils.StatementEntryItem
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.toInteroperableList
import kotlin.math.abs

open class CategoryEntryGenerator {

    fun <E> List<E>.fixedRandomShuffled(seed: Int): Collection<E> {
        val output = mutableSetOf<E>()
        for (el in this) {
            output.add(this[abs(el.hashCode() + seed) % size])
        }
        return output
    }

    protected fun entryOf(
        name: String,
        currency: Currency,
        randomizer: Int,
        pool: List<String>
    ): CategoryEntry {
        val seed = abs(randomizer + name.hashCode() + currency.hashCode())
        return CategoryEntry(name, currency, pool.fixedRandomShuffled(seed).take(seed % pool.size).map {
            StatementEntryItem(it, Money.of((abs(seed + it.hashCode()) % 1000) * 100, currency))
        }.toInteroperableList())
    }

    protected operator fun CategoryEntry.times(times: Double): CategoryEntry {
        return CategoryEntry(name, currency, items.map {
            StatementEntryItem(it.details, it.value * times)
        }.toInteroperableList())
    }
}