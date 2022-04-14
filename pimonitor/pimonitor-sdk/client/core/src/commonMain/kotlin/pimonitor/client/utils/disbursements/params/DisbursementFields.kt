@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.utils.disbursements.params

import datetime.DateFormatter
import pimonitor.core.utils.disbursements.Disbursement
import presenters.fields.DateInputField
import presenters.fields.NumberInputField
import kotlin.js.JsExport
import pimonitor.core.utils.disbursements.params.DisbursementRawParams as Params

class DisbursementFields(params: Params? = null, disbursement: Disbursement? = null) {
    val amount = NumberInputField(
        name = Params::amount,
        label = "Amount",
        value = params?.amount ?: disbursement?.amount?.toFormattedString()
    )
    val date = DateInputField(
        name = Params::date,
        label = "Disbursement Date",
        value = params?.date ?: disbursement?.date?.let {
            DateFormatter("{YYYY}-{MM}-{DD}").format(it)
        }
    )
}