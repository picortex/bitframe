@file:JsExport

package bitframe.monitors

import bitframe.authentication.users.UserRef
import contacts.Email
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
}