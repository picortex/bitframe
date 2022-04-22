package bitframe.client

fun <S : Any, T : Any> UIScopeConfig<S>.map(transformer: (S) -> T): UIScopeConfig<T> = UIScopeConfigImpl(
    service = transformer(service),
    viewModel = viewModel
)