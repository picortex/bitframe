package bitframe

import bitframe.annotations.BitframeServerDsl
import kotlin.experimental.ExperimentalTypeInference

@BitframeServerDsl
@OptIn(ExperimentalTypeInference::class)
inline fun <S> applicationKtor(
    @BuilderInference builder: ApplicationConfigBuilder<S>.() -> Unit
) = ApplicationKtor(ApplicationConfigBuilder<S>().apply(builder).buildApplicationConfig())