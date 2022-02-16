package pimonitor.server.mailing

import bitframe.core.ServiceConfig
import identifier.Email
import mailer.Mailer

interface MailingServiceConfig : ServiceConfig {
    val mailHost: Email
    val mailer: Mailer
}