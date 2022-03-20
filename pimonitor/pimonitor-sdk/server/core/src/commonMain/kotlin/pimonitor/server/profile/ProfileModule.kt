package pimonitor.server.profile

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class ProfileModule(
    private val controller: ProfileController
) : Module {
    val basePath = "/api/user/profile"
    override val name = "Profile"
    override val actions: List<Action> = listOf(
        Action("change-password", mapOf(), HttpRoute(HttpMethod.Post, "$basePath/change-password") {
            controller.changePassword(it)
        })
    )
}