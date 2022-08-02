@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.internal.AppScopeConfigImpl
import cache.Cache
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import logging.Logger
import viewmodel.ScopeConfig
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@JsExport
interface AppScopeConfig<out A> : ScopeConfig<A> {
    val cache: Cache
    val codec: StringFormat

    override fun <R> map(transformer: (A) -> R): AppScopeConfig<R>

    companion object {
        val DEFAULT_CODEC = Json

        @JvmStatic
        @JvmName("create")
        @JvmOverloads
        operator fun <A> invoke(
            api: A,
            cache: Cache,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER,
            codec: StringFormat = DEFAULT_CODEC
        ): AppScopeConfig<A> = AppScopeConfigImpl(api, cache, executor, logger, codec)
    }
}