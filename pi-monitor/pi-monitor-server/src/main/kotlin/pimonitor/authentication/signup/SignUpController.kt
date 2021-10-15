package pimonitor.authentication.signup

import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.response.response.response
import bitframe.server.http.HttpRequest
import bitframe.server.http.compulsoryBody
import bitframe.server.http.toHttpResponse
import bitframe.service.config.ServiceConfig
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.InternalServerError
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import later.await
import pimonitor.monitors.MonitorDao
import pimonitor.monitors.SignUpParams

private val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class SignUpController(
    private val service: SignUpService
) {
    constructor(
        config: ServiceConfig,
        service: UsersService,
        dao: MonitorDao,
        bus: EventBus
    ) : this(SignUpServiceImpl(dao, service, bus, config))

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun signUp(req: HttpRequest) = response {
        val params = try {
            json.decodeFromString<SignUpParams>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            json.decodeFromString<SignUpParams.Business>(req.compulsoryBody())
        } catch (err: Throwable) {
            null
        } ?: try {
            json.decodeFromString<SignUpParams.Individual>(req.compulsoryBody())
        } catch (err: Throwable) {
            reject(BadRequest, "Make sure you have the proper sign up params")
        }
        val conundrum = service.signUp(params).await()
        resolve(conundrum, Created)
    }.toHttpResponse()
}