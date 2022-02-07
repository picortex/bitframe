@file:JsExport

package pimonitor.monitors

import bitframe.actors.users.UserRef
import identifier.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class CooperateMonitor(
    override val uid: String,
    override val name: String,
    val email: Email,
    val contacts: List<ContactPerson>
) : Monitor() {
    @JsName("with")
    constructor(
        uid: String,
        name: String,
        email: Email,
        person: ContactPerson
    ) : this(uid, name, email, listOf(person))

    @Serializable
    data class ContactPerson(
        val uid: String,
        val name: String,
        val email: Email,
        val userRef: UserRef
    )

    override fun copy(id: String) = copy(uid = id)
}