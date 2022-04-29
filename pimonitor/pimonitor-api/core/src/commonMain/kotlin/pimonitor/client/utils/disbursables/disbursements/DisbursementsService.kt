@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursables.disbursements

import bitframe.core.CreatorUpdater
import bitframe.core.Deleter
import bitframe.core.IdentifiedRaw
import kotlinx.collections.interoperable.List
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceCore
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementRawParams
import kotlin.js.JsExport

@JsExport
interface DisbursementsService : DisbursementsServiceCore,
    CreatorUpdater<DisbursableDisbursementRawParams, Disbursement>,
    Deleter<IdentifiedRaw<Array<String>>, List<Disbursement>>