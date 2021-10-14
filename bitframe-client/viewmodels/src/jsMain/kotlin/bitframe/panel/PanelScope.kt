@file:JsExport

package bitframe.panel

import bitframe.BitframeService

class PanelScope(val service: BitframeService) {
    val viewModel = PanelViewModel(service)
}