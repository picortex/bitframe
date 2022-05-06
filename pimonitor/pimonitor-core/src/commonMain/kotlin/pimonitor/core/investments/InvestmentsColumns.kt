package pimonitor.core.investments

import presenters.money.toDefaultFormat
import presenters.table.builders.TableBuilder

fun TableBuilder<InvestmentSummary>.InvestmentsColumns(showBusinessColumn: Boolean) {
    column("Name") { it.data.name }
    if (showBusinessColumn) column("Business") { it.data.businessName }
    column("Source") { it.data.source }
    column("Type") { it.data.type }
    column("Amount") { it.data.amount.toDefaultFormat() }
    column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
    //        column("Progress") { "${it.data.disbursementProgressInPercentage.asInt}%" }
    column("Created By") { it.data.createdBy.name }
}