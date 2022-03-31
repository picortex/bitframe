@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.utils.disbursements

import presenters.fields.DateInputField
import presenters.fields.NumberInputField
import kotlin.js.JsExport
import pimonitor.core.business.utils.disbursements.CreateDisbursementParamsContextual as Params

class CreateDisbursementFields(
    val amount: NumberInputField = NumberInputField(
        name = Params::amount,
        label = "Amount"
    ),
    val date: DateInputField = DateInputField(
        name = Params::date,
        label = "Disbursement Date"
    )
)