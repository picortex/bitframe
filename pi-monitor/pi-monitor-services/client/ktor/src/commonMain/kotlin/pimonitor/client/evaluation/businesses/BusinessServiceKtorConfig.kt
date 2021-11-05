package pimonitor.client.evaluation.businesses

import bitframe.service.client.config.KtorClientConfiguration
import pimonitor.client.monitors.MonitorsServiceKtor

interface BusinessServiceKtorConfig : BusinessesServiceConfig, KtorClientConfiguration {
    override val monitorsService: MonitorsServiceKtor
}