@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursables.disbursements

import bitframe.core.*
import kotlinx.collections.interoperable.List
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import kotlin.js.JsExport

@JsExport
interface DisbursementsServiceCore :
    AuthorizedCreator<DisbursableDisbursementParams, Disbursement>,
    AuthorizedUpdater<Identified<DisbursableDisbursementParams>, Disbursement>,
    AuthorizedDeleter<Identified<Array<out String>>, List<Disbursement>>