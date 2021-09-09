package unit

import Configuration
import expect.expect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.coroutines.runTest
import service
import kotlin.test.Test

class ExportsTest {

    inline fun <T> json(builder: T.() -> Unit): T = js("{}").unsafeCast<T>().apply(builder)

    @Test
    fun should_get_a_service() {
        val cred: Configuration = json {
            appId = "12345"
        }
        console.log(cred)
        val service = service(cred)
        console.log(service)
        expect(service).toBeNonNull()
    }
}