package pimonitor.core.interventions

import presenters.money.toDefaultFormat
import presenters.table.builders.TableBuilder

private const val dateFormat = "{DD}-{MM}-{YYYY}"

fun TableBuilder<InterventionSummary>.InterventionsColumns(showBusinessColumn: Boolean) {
    column("Name") { it.data.name }
    if (showBusinessColumn) column("Business") { it.data.businessName }
    column("Amount") { it.data.amount.toDefaultFormat() }
    column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
    column("Goals") { "0/${it.data.goals.size}" }
    column("Start") { it.data.date.format(dateFormat) }
    column("Deadline") { it.data.deadline.format(dateFormat) }
    column("Countdown") { (it.data.deadline - it.data.date).toString() }
    column("Created By") { it.data.createdBy.name }
}