import expect.expect
import kotlin.test.Test

class ExportsTest {

    inline fun <T> json(builder: T.() -> Unit): T = js("{}").unsafeCast<T>().apply(builder)

    @Test
    fun should_get_a_service() {
        val cred: ServiceConfiguration = json {
            appId = "12345"
        }
        console.log(cred)
        val service = service(cred)
        console.log(service)
        expect(service).toBeNonNull()
    }
}