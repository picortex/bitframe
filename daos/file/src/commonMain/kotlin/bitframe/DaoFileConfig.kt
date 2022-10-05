package bitframe

import bitframe.actor.Savable
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path
import kotlin.reflect.KClass

interface DaoFileConfig<D : Any> : DaoConfig<D> {
    val directory: Path
    val fs: FileSystem
    val codec: StringFormat

    companion object {

        val DEFAULT_CODEC = Json {
            prettyPrint = true
        }

        operator fun <T : Savable> invoke(
            clazz: KClass<T>,
            fs: FileSystem,
            directory: Path,
            executor: Executor = DaoConfig.DEFAULT_EXECUTOR,
            codec: StringFormat = DEFAULT_CODEC
        ): DaoFileConfig<T> = DaoFileConfigImpl(clazz, executor, fs, directory, codec)

        inline operator fun <reified T : Savable> invoke(
            fs: FileSystem,
            directory: Path,
            executor: Executor = DaoConfig.DEFAULT_EXECUTOR,
            codec: StringFormat = DEFAULT_CODEC
        ): DaoFileConfig<T> = DaoFileConfigImpl(T::class, executor, fs, directory, codec)
    }
}