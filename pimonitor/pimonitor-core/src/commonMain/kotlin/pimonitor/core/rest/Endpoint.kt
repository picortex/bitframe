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
    val businessesDelete = "$businesses/delete"

    private val business = "$root/business"
    val businessOperationalDashboard = "$business/operations/dashboard"

    private val businessFinancialReports = "$business/financials/reports"
    val businessFinancialReportsAvailable = "$businessFinancialReports/available"
    val businessFinancialReportBalanceSheet = "$businessFinancialReports/balance-sheet"
    val businessFinancialReportIncomeStatement = "$businessFinancialReports/income-statement"
    val businessFinancialReportCashFlow = "$businessFinancialReports/cash-flow"

    private val businessInvestments = "$business/investments"
    val businessInvestmentsCapture = "$businessInvestments/capture"
    val businessInvestmentsAll = "$businessInvestments/all"
    val businessInvestmentsDisburse = "$businessInvestments/disburse"

    private val businessInterventions = "$business/interventions"
    val businessInterventionsCreate = "$businessInterventions/create"
    val businessInterventionsAll = "$businessInterventions/all"
    val businessInterventionsDisburse = "$businessInterventions/disburse"

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