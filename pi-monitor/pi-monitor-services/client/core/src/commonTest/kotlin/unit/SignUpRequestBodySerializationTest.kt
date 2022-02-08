package unit

import bitframe.service.requests.RequestBody
import kotlinx.serialization.json.Json
import pimonitor.authentication.signup.SignUpParams
import kotlin.test.Test

class SignUpRequestBodySerializationTest {

    private val json = Json { prettyPrint = true }

    @Test
    fun should_serialize_request_body_without_question() {
        val rb = RequestBody.UnAuthorized(
            appId = "test",
            data = SignUpParams.Individual("test", "test@test.com", "password")
        )
        val output = json.encodeToString(RequestBody.UnAuthorized.serializer(SignUpParams.serializer()), rb)
        println(output)
    }
}