@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package pimonitor.core.businesses

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import events.Event
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.businesses.params.InviteToShareReportsRawParams
import pimonitor.core.invites.Invite
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface BusinessesServiceCore {
    @JsName("_ignore_create")
    fun create(rb: RequestBody.Authorized<CreateMonitoredBusinessParams>): Later<CreateMonitoredBusinessResult>

    @JsName("_ignore_invite")
    fun invite(rb: RequestBody.Authorized<InviteToShareReportsParams>): Later<Invite>

    @JsName("_ignore_all")
    fun all(rb: RequestBody.Authorized<BusinessFilter>): Later<List<MonitoredBusinessSummary>>

    @JsName("_ignore_delete")
    fun delete(rb: RequestBody.Authorized<Array<out String>>): Later<List<MonitoredBusinessBasicInfo>>
}