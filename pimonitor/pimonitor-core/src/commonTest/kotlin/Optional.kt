import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A multi-platform Optional type, because serializing optional generic types does not work well.
 * In addition to not being multi-platform, java.util.Optional doesn't actually support *Present*
 * null values, resulting in type warnings from Kotlin (which will, in the future, be errors).
 *
 * See:
 * https://github.com/Kotlin/kotlinx.serialization/issues/1784 (for closed polymorphism) and
 * https://github.com/Kotlin/kotlinx.serialization/issues/944 (for open polymorphism)
 */
@Serializable(with = OptionalSerializer::class)
sealed class Optional<out T> {
    companion object {
        fun <T> of(value: T?): Optional<T> = if (value != null) Present(value) else Empty()
        fun absent(): Optional<Nothing> = Absent
    }

    /**
     * Get a non-null value i.e. must be Present. If the [Optional] is either [Empty] or [Absent]
     * throws a [NoSuchElementException].
     */
    fun get(): T = if (this is Present) this.value else throw NoSuchElementException()

    /**
     * Get a non-null value if it is [Present], otherwise if the [Optional] is either [Empty] or [Absent]
     * returns null.
     */
    fun getOrNull(): T? = if (this is Present) this.value else null

    fun isPresent(): Boolean = this is Present

    fun isEmpty(): Boolean = this is Empty

    fun isAbsent(): Boolean = this is Absent
}

/**
 * A Present [Optional].
 */
@Serializable
@SerialName("Present")
data class Present<out T : Any>(val value: T) : Optional<T>()

/**
 * An Empty [Optional], which is like [Present] with a null value, but having it as another type supports serialization.
 */
@Serializable
@SerialName("Empty")
class Empty<out T> : Optional<T>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        return true
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }
}

/**
 * An absent [Optional].
 */
@Serializable
@SerialName("Absent")
object Absent : Optional<Nothing>()