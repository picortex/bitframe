package pimonitor.evaluation.businesses

import pimonitor.client.PiMonitorService
import react.RBuilder

fun RBuilder.InviteBusiness(service: PiMonitorService, uid: String?) = AddBusiness(service, uid)