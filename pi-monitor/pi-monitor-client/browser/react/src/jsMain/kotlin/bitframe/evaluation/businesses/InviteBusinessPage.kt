package bitframe.evaluation.businesses

import bitframe.client.PiMonitorService
import react.RBuilder

fun RBuilder.InviteBusiness(service: PiMonitorService, uid: String?) = AddBusiness(service, uid)