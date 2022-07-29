@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.internal.AppScopeConfigImpl
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import logging.Logger
import presenters.forms.FormConfig
import viewmodel.ScopeConfig
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@JsExport
interface AppScopeConfig<out A> : ScopeConfig<A> {
    val codec: StringFormat

    companion object {
        val DEFAULT_CODEC = Json

        @JvmStatic
        @JvmName("create")
        @JvmOverloads
        operator fun <A> invoke(
            api: A,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER,
            codec: StringFormat = DEFAULT_CODEC
        ): AppScopeConfig<A> = AppScopeConfigImpl(api, executor, logger, codec)
    }
}