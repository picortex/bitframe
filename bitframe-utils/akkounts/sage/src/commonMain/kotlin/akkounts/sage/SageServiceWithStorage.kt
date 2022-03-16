package akkounts.sage
//
//import akkounts.*
//import akkounts.regulation.QueryRegulator
//import akkounts.reports.balancesheet.BalanceSheet
//import akkounts.reports.balancesheet.BalanceSheetStorage
//import akkounts.reports.incomestatement.IncomeStatement
//import akkounts.reports.incomestatement.IncomeStatementStorage
//import akkounts.sage.reports.balancesheet.BalanceSheetParser
//import akkounts.sage.reports.incomestatement.IncomeStatementParser
//import io.ktor.client.*
//import io.ktor.client.request.*
//import io.ktor.http.*
//import io.ktor.http.content.*
//import io.ktor.util.*
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.SupervisorJob
//import kotlinx.datetime.*
//import kotlinx.serialization.mapper.Mapper
//import later.Later
//import later.await
//import later.later
//import kotlin.jvm.JvmOverloads
//
//class SageServiceWithStorage @JvmOverloads constructor(
//    private val environment: Environment = Environment.PROD,
//    /** Customer data as we have stored it in our system */
//    private val owner: Owner,
//    private val credentials: Credentials,
//    private val regulator: QueryRegulator,
//    private val balanceSheetStorage: BalanceSheetStorage,
//    private val incomeStatementStorage: IncomeStatementStorage
//) {
//    companion object {
//        const val VENDOR_NAME = "Sage"
//    }
//
//    private val scope = CoroutineScope(SupervisorJob())
//    private val client = HttpClient { }
//
//    @OptIn(InternalAPI::class)
//    private fun HttpRequestBuilder.authorize() {
//        header("Authorization", "Basic ${"${credentials.username}:${credentials.password}".encodeBase64()}")
//    }
//
//    fun balanceSheet(at: LocalDate): Later<BalanceSheet?> = scope.later {
//        val status = regulator.checkRestriction(owner.uid).await()
//        if (status == QueryRegulator.Status.Blocked) {
//            return@later null
//        }
//        val bsp = BalanceSheetParser(trialBalance(from = (at - DatePeriod(days = 30)), to = at).await())
//        val sheet = balanceSheetStorage.save(
//            owner = owner,
//            vendor = SageOneZAService.VENDOR,
//            data = BalanceSheet.Data(
//                assets = bsp.assets(),
//                equity = bsp.equity(),
//                liabilities = bsp.liabilities()
//            )
//        )
//        regulator.incrementCounter(owner.uid).await()
//        sheet.await()
//    }
//
//    fun incomeStatement(from: LocalDate, to: LocalDate) = scope.later {
//        val status = regulator.checkRestriction(owner.uid).await()
//        if (status == QueryRegulator.Status.Blocked) {
//            return@later null
//        }
//        val company = company(credentials.companyId)
//        val isp = IncomeStatementParser(trialBalance(from, to).await())
//        val data = IncomeStatement.Data(
//            income = isp.income(),
//            otherIncome = CategoryEntry(emptyList()),
//            costOfSales = CategoryEntry(emptyList()),
//            expenses = isp.expenses(),
//            otherExpenses = CategoryEntry(emptyList()),
//            taxes = isp.taxes(),
//        )
//        val statement = incomeStatementStorage.save(owner, SageOneZAService.VENDOR, data)
//        regulator.incrementCounter(owner.uid).await()
//        statement.await()
//    }
//
//    fun trialBalance(from: LocalDate, to: LocalDate) = scope.later {
//        val apiKey = credentials.apiKey
//        val companyId = credentials.companyId
//        val url = "${environment.path}/TrialBalance/Export?APIKey=$apiKey&companyid=${companyId}"
//        val response = client.post<String>(url) {
//            authorize()
//            body = TextContent(
//                text = """
//                {
//                  "FromDate": "${from.toYYYYMMDD()}",
//                  "ToDate": "${to.toYYYYMMDD()}",
//                  "ShowMovement": true,
//                  "DisplayReportingGroupDetail": true,
//                  "Comparative": true,
//                  "CurrencyId": 1
//                }""".trimIndent(),
//                contentType = ContentType.Application.Json
//            )
//        }
//        Mapper.decodeFromString("""{"response":$response}""")["response"] as List<Map<String, *>>
//    }
//
//    fun company(id: String) = scope.later {
//        val apiKey = credentials.apiKey
//        val url = "${environment.path}/Company/Get/$id?APIKey=$apiKey"
//        val json = client.get<String>(url) { authorize() }
//        Mapper.decodeFromString(json)
//    }
//
//    fun accounts() = scope.later {
//        val apiKey = credentials.apiKey
//        val companyId = credentials.companyId
//        val url = "${environment.path}/Account/GetWithSystemAccounts?APIKey=$apiKey&companyid=${companyId}"
//        client.get<String>(url) { authorize() }
//    }
//}