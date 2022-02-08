package pimonitor.authentication.signup

import response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.service.requests.RequestBody
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await
import pimonitor.server.authentication.signup.SignUpService

private val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class SignUpController(
    private val service: SignUpService
) {
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun signUp(req: HttpRequest) = response {
        val params = try {
            json.decodeFromString<RequestBody.UnAuthorized<SignUpParams>>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            json.decodeFromString<RequestBody.UnAuthorized<SignUpParams.Business>>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            json.decodeFromString<RequestBody.UnAuthorized<SignUpParams.Individual>>(req.compulsoryBody())
        } catch (err: Throwable) {
            reject(BadRequest, "Make sure you have the proper sign up params")
        }
        val conundrum = service.signUp(params).await()
        resolve(conundrum, Created)
    }.toHttpResponse()
}