package signup

import bitframe.core.RequestBody
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.signup.params.IndividualSignUpParams
import kotlin.test.Test

class SignUpRequestBodySerializationTest {

    private val json = Json { prettyPrint = true }

    @Test
    fun should_serialize_request_body_without_question() {
        val rb = RequestBody.UnAuthorized(
            appId = "test",
            data = IndividualSignUpParams("test", "test@test.com", "password")
        )
        val output = json.encodeToString(rb)
        println(output)
    }
}