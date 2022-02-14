package pimonitor.server.comms.mailing

import bitframe.core.service.ServiceConfig
import identifier.Email
import mailer.Mailer

interface MailingServiceConfig : ServiceConfig {
    val mailHost: Email
    val mailer: Mailer
}