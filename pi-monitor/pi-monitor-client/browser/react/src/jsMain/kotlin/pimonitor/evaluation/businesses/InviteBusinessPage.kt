package pimonitor.evaluation.businesses

import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import react.RBuilder

fun RBuilder.InviteBusiness(config: PiMonitorViewModelConfig, uid: String?) = AddBusiness(config, uid)