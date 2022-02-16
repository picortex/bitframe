package bitframe.client.configurators

import bitframe.client.BitframeApi
import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeViewModelConfig
import bitframe.client.configurators.ApiConfigurator
import kotlin.js.JsExport

fun SdkConfigurator.toViewModelConfig(mode: ApiMode) = BitframeViewModelConfig(
    recoveryTime = recoveryTime?.toLong() ?: BitframeViewModelConfig.DEFAULT_RECOVERY_TIME,
    transitionTime = transitionTime?.toLong() ?: BitframeViewModelConfig.DEFAULT_RECOVERY_TIME,
    logger = mode.logger,
    scopeBuilder = { mode.scope }
)

//fun <A : BitframeApi> SdkConfigurator.toAppScopeConfig(api: A): BitframeAppScopeConfig<A> {
//    val apiConfigurator = toValidApiConfigurator()
//    val apiMode = apiConfigurator.toApiMode()
//    return BitframeAppScopeConfig(
//        api = api,
//        viewModel = toViewModelConfig(apiMode)
//    )
//}