package pimonitor.core.investments

import presenters.money.toDefaultFormat
import presenters.table.builders.TableBuilder

fun TableBuilder<InvestmentSummary>.InvestmentsColumns(
    showBusinessColumn: Boolean,
    extended: Boolean
) {
    column("Name") { it.data.name }
    if (showBusinessColumn || extended) column("Business") { it.data.businessName }
    if (extended) column("Source") { it.data.source }
    column("Type") { it.data.type }
    column("Amount") { it.data.amount.toDefaultFormat() }
    if (extended) column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
    if (extended) column("Created By") { it.data.createdBy.name }
}