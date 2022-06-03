@file:JsExport

package pimonitor.core.businesses.params

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class CreateMonitoredBusinessParams(
    override val businessName: String,
    override val contactName: String,
    override val contactEmail: String,
    override val sendInvite: Boolean = true
) : CreateMonitoredBusinessRawParams