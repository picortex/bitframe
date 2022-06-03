package pimonitor

import identifier.toName
import later.await
import pimonitor.client.MonitorApi
import presenters.table.builders.tableOf
import presenters.table.tabulateToConsole

suspend fun MonitorApi.populateFor(operator: Operator) {
    signOut.signOut()
    createAndSignIn(operator)
    registerThenInviteThenAcceptBusiness(DemoBusiness.defaults)
    val session = config.session.value
    val user = session.user?.name?.toName()?.first
    val space = session.space?.name
    print("${user}'s Businesses [$space]")
    tableOf(businesses.all().await()) {
        column("No") { it.number.toString() }
        column("Operational") { it.data.operationalBoard }
        column("Financial") { it.data.financialBoard }
        column("Name") { it.data.name }
    }.tabulateToConsole()
}