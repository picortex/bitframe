@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.internal.AppScopeConfigImpl
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@JsExport
interface AppScopeConfig<out A> : ViewModelConfig<A> {
    companion object {
        @JvmStatic
        @JvmName("create")
        @JvmOverloads
        operator fun <A> invoke(
            api: A,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER,
            codec: StringFormat = ViewModelConfig.DEFAULT_CODEC
        ): AppScopeConfig<A> = AppScopeConfigImpl(api, executor, logger, codec)
    }
}