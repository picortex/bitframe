package bitframe.client.profile

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.client.rest.pathV1
import bitframe.core.RequestBody
import bitframe.core.profile.params.ChangePasswordParams
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.later
import response.decodeResponseFromString

class ProfileServiceKtor(
    private val config: ServiceConfigKtor
) : ProfileService(config) {
    val json get() = config.json
    val client get() = config.http

    @OptIn(InternalAPI::class)
    override fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>) = config.scope.later {
        val req = client.post(config.pathV1.changePassword) {
            body = json.of(rb)
        }
        json.decodeResponseFromString(ChangePasswordParams.serializer(), req.bodyAsText()).response()
    }
}