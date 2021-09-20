@file:JsExport

package bitframe.panel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viewmodel.ViewModel
import kotlin.js.JsExport
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelViewModel : ViewModel<Intent, State>(State.Loading("Setting up your workspace")) {
    init {
        coroutineScope.launch {
            delay(500)
            ui.value = State.Panel(
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