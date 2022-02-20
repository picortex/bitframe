package bitframe.client.profile

import bitframe.client.KtorServiceConfig
import bitframe.client.ServiceConfig
import bitframe.client.of
import bitframe.core.RequestBody
import bitframe.core.profile.params.ChangePasswordParams
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import response.decodeResponseFromString

class ProfileServiceKtor(
    private val config: KtorServiceConfig
) : ProfileService(config) {
    val basePath = "/api/user/profile"
    val json get() = config.json
    val client get() = config.http

    @OptIn(InternalAPI::class)
    override fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>) = config.scope.later {
        val req = client.post("${config.url}$basePath/change-password") {
            body = json.of(rb)
        }
        json.decodeResponseFromString(ChangePasswordParams.serializer(), req.bodyAsText()).response()
    }
}