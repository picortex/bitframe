package bitframe

import bitframe.dao.internal.DaoConfigImpl
import koncurrent.Executor
import koncurrent.Executors
import kotlin.jvm.*
import kotlin.reflect.KClass

interface DaoConfig<D : Any> {
    val clazz: KClass<D>
    val executor: Executor

    companion object {
        @JvmField
        val DEFAULT_EXECUTOR = Executors.default()

        @JvmName("create")
        @JvmOverloads
        @JvmSynthetic
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            executor: Executor = DEFAULT_EXECUTOR
        ): DaoConfig<D> = DaoConfigImpl(clazz, executor)

        inline operator fun <reified D : Any> invoke(executor: Executor = DEFAULT_EXECUTOR) = invoke(D::class, executor)
    }
}