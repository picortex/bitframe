package pimonitor.server.contacts

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import pimonitor.server.utils.pathV1

class ContactsModule(
    private val controller: ContactsController
) : Module {
    override val name: String = "Contacts"
    private val config get() = controller.config
    override val actions: List<Action> = listOf(
        Action("all", mapOf(), HttpRoute(HttpMethod.Post, config.pathV1.contactsAll) {
            controller.all(it)
        })
    )
}