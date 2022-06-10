package bitframe.server

import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
inline fun <S> bitframeApplication(
    @BuilderInference builder: KtorServerConfigurationBuilder<S>.() -> Unit
): Application<S> {
    val configBuilder = KtorServerConfigurationBuilder<S>().apply(builder)
    val applicationConfig = configBuilder.buildApplicationConfig().apply {
        val service = configBuilder.buildService()
        configBuilder.moduleBuilders.forEach {
            modules.add(it(service))
        }
    }
    return Application(applicationConfig)
}