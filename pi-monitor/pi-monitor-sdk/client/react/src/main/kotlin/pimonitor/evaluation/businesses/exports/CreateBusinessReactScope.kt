@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.evaluation.businesses.exports

import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessReactScope(
    config: PiMonitorViewModelConfig
) : CreateBusinessScope(config), ReactUIScope<Intent, State>