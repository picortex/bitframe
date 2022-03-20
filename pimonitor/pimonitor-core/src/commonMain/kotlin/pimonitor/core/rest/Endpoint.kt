package pimonitor.core.rest

sealed class Endpoint(private val root: String) {
    class Client(url: String, version: String) : Endpoint("$url/api/$version")
    class Server(version: String) : Endpoint("/api/$version")

    //businesses
    private val businesses = "$root/businesses"
    val businessesAll = "$businesses/all"
    val businessesCreate = "$businesses/create"
    val businessesLoad = "$businesses/load"
    val businessesAvailableReports = "$businesses/available-reports"
    val businessesDelete = "$businesses/delete"
    val businessesDashboardOperational = "$businesses/dashboard/operational"
    val businessesIncomeStatement = "$businesses/income-statement"
    val businessesBalanceSheet = "$businesses/balance-sheet"

    // invites
    val invitesAcceptSage = "$root/invites/accept/sage"
    val invitesAcceptPicortex = "$root/invites/accept/picortex"
    val invitesLoad = "$root/invites/load"
    val invitesDefaultMessage = "$root/invites/message-default"
    val invitesSend = "$root/invites/send"
}