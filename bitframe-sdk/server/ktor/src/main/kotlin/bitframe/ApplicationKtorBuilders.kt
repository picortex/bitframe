package bitframe

import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
inline fun <S> applicationKtor(
    @BuilderInference builder: ApplicationConfigBuilder<S>.() -> Unit
) = ApplicationKtor(ApplicationConfigBuilder<S>().apply(builder).buildApplicationConfig())