package bitframe.server

import java.io.File

internal class KtorServerConfigurationBuilderImpl<S>(
    override var public: File?
) : KtorServerConfigurationBuilder<S>, ServerConfigurationBuilder<S> by ServerConfigurationBuilder()