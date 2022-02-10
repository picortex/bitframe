package pimonitor.server.comms.mailing

import later.Later
import later.await
import later.later
import mailer.EmailDraft
import mailer.EmailMessage
import pimonitor.monitored.MonitoredBusiness

class MailingService(val config: MailingServiceConfig) {

    val mailer get() = config.mailer
    val scope get() = config.scope

//    fun sendInviteToShareReports(monitor: Monitor, monitored: MonitoredBusiness): Later<EmailMessage> = scope.later {
//        val contact = monitored.contacts.first()
//        mailer.send(
//            draft = EmailDraft(
//                subject = "Invite to Share Reports",
//                body = """
//                Dear ${contact.name},
//                ${monitor.name} has invited you to share your reports with them through PiMonitor
//
//                You can share reports with us with the following options
//                - PiCortex (https://picortex.com)
//                - Sage (our sage url goes here)
//                - Xero (coming soon)
//
//                We hope to here soon from you,
//
//                Regards
//                ${monitor.name}
//            """.trimIndent()
//            ),
//            from = config.mailHost,
//            to = contact.email
//        ).await()
//    }
}