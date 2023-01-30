package bitframe

import bitframe.actor.Identified
import koncurrent.Later
import bitframe.params.ContactParams
import kollections.List
import kollections.Map
import kronecker.LoadOptions

interface ContactsApi {

    /**
     * params.uid -> Should be the corporate id
     */
    fun create(params: Identified<String, ContactParams>): Later<Contact>

    /**
     * params.uid -> Should be the contact id
     */
    fun edit(params: Identified<String, ContactParams>): Later<Contact>

    /**
     * params.uid -> should be contact uid that you are adding comms
     */
    fun add(params: Identified<String, Comm>): Later<Contact>

    /**
     * params.uid -> should be contact uid that you are adding comms
     */
    fun remove(params: Identified<String, Comm>) : Later<Contact>

    fun delete(uid: String) : Later<Contact>

    fun loadAllInCorporate(params: String) : Later<List<Contact>>

    fun loadAll(option: LoadOptions? = null) : Later<Map<Contact,Corporate>>
}