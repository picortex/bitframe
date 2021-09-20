package pimonitor.test.evaluation

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import contacts.EmailGenerator
import contacts.Name
import contacts.NameGenerator
import later.Later
import pimonitor.Monitor
import pimonitor.evaulation.business.BusinessService

class BusinessServiceTestImpl(
    val configuration: TestClientConfiguration
) : BusinessService {
    companion object {
        val suffix = listOf("Inc", "Ltd", "Corp", "LLC")
        fun randBusiness(name: Name = NameGenerator.random()): Monitor.Business {
            val busName = "${name.full} ${suffix.random()}"
            return Monitor.Business(
                name = "$busName.",
                email = EmailGenerator.generateFor(busName)
            )
        }
    }

    override val config: ClientConfiguration = configuration

    @OptIn(ExperimentalStdlibApi::class)
    override fun all(): Later<List<Monitor.Business>> = Later.resolve(
        buildList {
            repeat(20) { add(randBusiness()) }
        }
    )
}