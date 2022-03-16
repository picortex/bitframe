package pimonitor.server

import bitframe.core.profile.ProfileDaodService
import bitframe.server.BitframeService
import bitframe.server.ServiceConfig
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.contacts.ContactsDaodService
import pimonitor.core.invites.InvitesDaodService
import pimonitor.core.portfolio.PortfolioDaodService
import pimonitor.core.search.SearchDaodService
import pimonitor.core.signup.SignUpDaodService

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signup by lazy { SignUpDaodService(config) }
    val businesses by lazy { BusinessesDaodService(config) }
    val profile by lazy { ProfileDaodService(config) }
    val contacts by lazy { ContactsDaodService(config) }
    val portfolio by lazy { PortfolioDaodService(config) }
    val search by lazy { SearchDaodService(config) }
    val invites by lazy { InvitesDaodService(config) }
}