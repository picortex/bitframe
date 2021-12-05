import expect.expect
import identifier.Email
import kotlinx.coroutines.runTest
import later.await
import mailer.EmailDraft
import mailer.Mailer
import mailer.MockMailer
import mailer.MockMailerConfig
import kotlin.test.Test

class MailerMockTest {
    val config = MockMailerConfig(
        separator = "= = = = = = = = = ="
    )
    val mailer: Mailer = MockMailer(config)

    @Test
    fun should_easily_send_an_email() = runTest {
        val message = mailer.send(
            draft = EmailDraft(
                subject = "Test Draft",
                body = "This is a test email"
            ),
            from = Email("from@test.com"),
            to = Email("to@gmail.com"),
        ).await()
        expect(message).toBeNonNull()
    }
}