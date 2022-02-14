package bitframe.server.testing

import bitframe.core.daos.MockDaoFactory
import bitframe.server.*
import bitframe.service.server.config.ServiceConfig
import java.io.File

fun main(vararg args: String) {
    bitframeApplication {
        public = File(args[0])
        database { MockDaoFactory() }
        service { factory ->
            BitframeService(ServiceConfig(factory))
        }
    }.start()
}