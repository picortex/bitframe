@file:JsExport

package pimonitor.client.contacts

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import useViewModelState
import pimonitor.client.contacts.ContactsIntent as Intent
import pimonitor.client.contacts.ContactsState as State

class ContactsReactScope(
    override val config: UIScopeConfig<ContactsService>
) : ContactsScope(config), ReactUIScope<Intent, State> {
    override val useScopeState: () -> State = {
        useViewModelState(viewModel)
    }
}