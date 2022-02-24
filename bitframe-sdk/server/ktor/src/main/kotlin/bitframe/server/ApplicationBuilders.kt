package bitframe.server

import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
inline fun <S : BitframeService> bitframeApplication(
    @BuilderInference builder: KtorServerConfigurationBuilder<S>.() -> Unit
): Application<S> {
    val configBuilder = KtorServerConfigurationBuilder<S>().apply(builder)
    val applicationConfig = configBuilder.buildApplicationConfig().apply {
        configBuilder.moduleBuilders.forEach {
            modules.add(it(configBuilder.buildService()))
        }
    }
    return Application(applicationConfig)
}