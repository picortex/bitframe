package pimonitor.server.contacts

import bitframe.server.Action
import bitframe.server.Module
import bitframe.server.http.HttpRoute
import io.ktor.http.*

class ContactsModule(
    private val controller: ContactsController
) : Module {
    override val name: String = "Contacts"
    override val actions: List<Action> = listOf(
        Action("all", mapOf(), HttpRoute(HttpMethod.Post, "/api/contacts/all") {
            controller.all(it)
        })
    )
}