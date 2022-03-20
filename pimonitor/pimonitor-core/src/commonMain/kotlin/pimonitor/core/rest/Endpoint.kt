package pimonitor.core.rest

sealed class Endpoint(root: String) {
    class Client(url: String, version: String) : Endpoint("$url/api/$version")
    class Server(version: String) : Endpoint("/api/$version")

    val signUpIndividual = "$root/sign-up/individual"
    val signUpBusiness = "$root/sign-up/business"

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

    // contacts
    val contactsAll = "$root/contacts/all"

    // invites
    val invitesAcceptSage = "$root/invites/accept/sage"
    val invitesAcceptPicortex = "$root/invites/accept/picortex"
    val invitesLoad = "$root/invites/load"
    val invitesDefaultMessage = "$root/invites/message-default"
    val invitesSend = "$root/invites/send"

    // portfolio
    val portfolioLoad = "$root/portfolio/load"

    // search
    val search = "$root/search"
}