package bitframe.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import kotlin.reflect.KClass

interface DaoConfig<D : Any> {
    val clazz: KClass<D>
    val scope: CoroutineScope

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())

        @JvmSynthetic
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : DaoConfig<D> {
            override val clazz = clazz
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun <D : Any> create(
            clazz: KClass<D>,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(clazz, scope)
    }
}