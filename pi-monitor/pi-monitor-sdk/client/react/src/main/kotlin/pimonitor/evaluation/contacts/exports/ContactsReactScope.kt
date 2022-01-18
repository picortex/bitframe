@file:JsExport

package pimonitor.evaluation.contacts.exports

import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import useViewModelState
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

class ContactsReactScope internal constructor(config: PiMonitorViewModelConfig) : ContactsScope(config), ReactUIScope<Intent, State> {
    override val useStateFromViewModel: () -> State = {
        useViewModelState(viewModel)
    }
}