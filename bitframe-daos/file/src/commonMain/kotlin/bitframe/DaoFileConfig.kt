package bitframe

import bitframe.actor.Savable
import koncurrent.Executor
import okio.FileSystem
import kotlin.reflect.KClass

interface DaoFileConfig<D : Any> : DaoConfig<D> {
    val fs: FileSystem

    companion object {

        operator fun <T : Savable> invoke(
            clazz: KClass<T>,
            fs: FileSystem,
            executor: Executor = DaoConfig.DEFAULT_EXECUTOR
        ): DaoFileConfig<T> = DaoFileConfigImpl(clazz, executor, fs)

        inline operator fun <reified T : Savable> invoke(
            fs: FileSystem,
            executor: Executor = DaoConfig.DEFAULT_EXECUTOR
        ): DaoFileConfig<T> = DaoFileConfigImpl(T::class, executor, fs)
    }
}