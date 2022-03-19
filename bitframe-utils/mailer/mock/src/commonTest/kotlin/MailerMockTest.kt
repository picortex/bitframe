import expect.expect
import identifier.Email
import kotlinx.coroutines.test.runTest
import later.await
import mailer.*
import kotlin.test.Ignore
import kotlin.test.Test

class MailerMockTest {
    val config = MockMailerConfig(
        simulationTime = 1000L,
        separator = "="
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

    @Test
    fun should_look_good_even_on_the_console() = runTest {
        val cfg = MockMailerConfig(charsPerLine = 55)
        val m = MockMailer(cfg)
        val draft = EmailDraft(
            subject = "Look good while doing it",
            body = "When you decide to do something, make sure you do it well and make sure you look good doing it\n" +
                    "It not only makes thr whole thing wow, but even people watching you do enjoy"
        )
        m.send(
            draft,
            from = AddressInfo(
                name = "Dope Developer",
                email = "anderson@developer.com"
            ), to = AddressInfo(
                name = "Console",
                email = "test@console.com"
            )
        ).await()
    }
}