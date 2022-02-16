import expect.expect
import identifier.Email
import kotlinx.coroutines.test.runTest
import later.await
import mailer.EmailDraft
import mailer.Mailer
import mailer.SmtpMailer
import mailer.SmtpMailerConfig
import java.util.*
import kotlin.test.Ignore
import kotlin.test.Test

class SmtpMailerTest {
    val prop = Properties().apply {
        val inStream = this@SmtpMailerTest::class.java.getResourceAsStream("sendgrid.properties")
        println(inStream)
        load(inStream)
    }

    val config = SmtpMailerConfig(prop)

    val mailer: Mailer = SmtpMailer(config)

    @Test
    @Ignore("This would be taking a lot of credit running it over and over")
    fun should_send_an_email() = runTest {
        val cfg = config.toProperties()
        println(cfg)
        val message = mailer.send(
            draft = EmailDraft(
                subject = "Test Draft",
                body = "This is a test email"
            ),
            from = Email("support@picortex.com"),
            to = Email("andylamax@programmer.net"),
        ).await()
        expect(message).toBeNonNull()
    }
}