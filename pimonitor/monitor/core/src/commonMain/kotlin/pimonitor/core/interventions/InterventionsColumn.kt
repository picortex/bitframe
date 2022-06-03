package pimonitor.core.interventions

import presenters.money.toDefaultFormat
import presenters.table.builders.TableBuilder

private const val dateFormat = "{DD}-{MM}-{YYYY}"

fun TableBuilder<InterventionSummary>.InterventionsColumns(
    showBusinessColumn: Boolean,
    extended: Boolean
) {
    column("Name") { it.data.name }
    if (showBusinessColumn || extended) column("Business") { it.data.businessName }
    column("Amount") { it.data.amount.toDefaultFormat() }
    if (extended) column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
    if (extended) column("Goals") { "0/${it.data.goals.size}" }
    if (extended) column("Start") { it.data.date.format(dateFormat) }
    if (extended) column("Deadline") { it.data.deadline.format(dateFormat) }
    column("Countdown") { (it.data.deadline - it.data.date).toString() }
    if (extended) column("Created By") { it.data.createdBy.name }
}