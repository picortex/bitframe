@file:JsExport

package bitframe.panel

import bitframe.BitframeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelViewModel(
    val service: BitframeService
) : ViewModel<Intent, State>(State.Loading("Setting up your workspace")) {
    init {
        coroutineScope.launch {
            delay(500)
            ui.value = State.Panel(
                service.signIn.session.value,
                listOf(
                    UIModuleGroup(
                        "Evaluation", listOf(
                            UIModule("Business", "")
                        )
                    ),
                    UIModuleGroup(
                        "Administrator", listOf(
                            UIModule("Users", "")
                        )
                    ),
                    UIModuleGroup(
                        "Personal", listOf(
                            UIModule("Setting", ""),
                            UIModule("My Profile", ""),
                            UIModule("Test Module", ""),
                            UIModule("George Module", "")
                        )
                    )
                )
            )
        }
    }

    override fun CoroutineScope.execute(i: Intent): Any {
        TODO("Not yet implemented")
    }
}