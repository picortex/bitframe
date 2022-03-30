package pimonitor.client.business.utils.disbursements

import presenters.fields.DateInputField
import presenters.fields.MoneyInputField
import pimonitor.core.business.utils.disbursements.CreateDisbursementParamsContextual as Params

class CreateDisbursementFields(
    val amount: MoneyInputField = MoneyInputField(
        name = Params::amount,
        label = "Amount"
    ),
    val date: DateInputField = DateInputField(
        name = Params::date,
        label = "Disbursement Date"
    )
)