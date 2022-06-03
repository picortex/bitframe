@file:JsExport

package pimonitor.client.contacts

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import viewmodel.asState
import pimonitor.client.contacts.ContactsState as State

class ContactsReactScope(
    override val config: UIScopeConfig<ContactsService>
) : ContactsScope(config), ReactUIScope<State> {
    override val useScopeState = { viewModel.asState() }
}