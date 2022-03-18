package akkounts.sage.reports

import akkounts.reports.utils.CategoryEntry
import akkounts.regulation.QueryRegulator
import akkounts.provider.Owner
import akkounts.reports.services.ReportsService
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.sage.Credentials
import akkounts.sage.SageOneZAService
import akkounts.sage.reports.balancesheet.BalanceSheetParser
import akkounts.sage.reports.incomestatement.IncomeStatementParser
import akkounts.utils.toYYYYMMDD
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import kash.Currency
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.serialization.mapper.Mapper
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class SageOneZAReportsService @JvmOverloads constructor(
    private val owner: Owner,
    private val credentials: Credentials,
    private val environment: SageOneZAService.Environment = SageOneZAService.Environment.PROD,
    private val regulator: QueryRegulator = QueryRegulator.FORGIVING,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    private val client: HttpClient = HttpClient { }
) : ReportsService {

    @OptIn(InternalAPI::class)
    private fun HttpRequestBuilder.authorize() {
        header("Authorization", "Basic ${"${credentials.username}:${credentials.password}".encodeBase64()}")
    }

    override fun balanceSheet(at: LocalDate): Later<BalanceSheet> = scope.later {
        val bsp = BalanceSheetParser(trialBalance(from = (at - DatePeriod(days = 30)), to = at).await())
        val header = BalanceSheet.Header(
            vendor = SageOneZAService.VENDOR,
            owner = owner,
            currency = Currency.ZAR,
            endOf = at
        )
        val data = BalanceSheet.Body(
            assets = bsp.assets(),
            equity = bsp.equity(),
            liabilities = bsp.liabilities()
        )
        regulator.incrementCounter(owner, SageOneZAService.VENDOR).await()
        BalanceSheet(uid = "<unset>", header, data)
    }

    override fun incomeStatement(start: LocalDate, end: LocalDate) = scope.later {
        val tb = trialBalance(start, end).await()
        println(Mapper.encodeToString(tb))
        val isp = IncomeStatementParser(tb)
        val header = IncomeStatement.Header(
            vendor = SageOneZAService.VENDOR,
            currency = Currency.ZAR,
            owner = owner,
            start = start,
            end = end
        )

        val taxes = isp.taxes()

        val rawIncome = isp.income()

        println(rawIncome)
        val income = rawIncome.items.map {
            if (it.details == "Sales") {
                it.copy(amount = it.amount + taxes.total)
            } else {
                it
            }
        }.toInteroperableList()
        println(income)

        val data = IncomeStatement.Body(
            income = CategoryEntry(income),
            otherIncome = CategoryEntry(emptyList()),
            costOfSales = CategoryEntry(emptyList()),
            expenses = isp.expenses(),
            otherExpenses = CategoryEntry(emptyList()),
            taxes = isp.taxes()
        )
        IncomeStatement(uid = "<unset>", header, data)
    }

    override fun cashFlow(start: LocalDate, end: LocalDate): Later<CashFlow> = scope.later {
//        trialBalance(start, end).await()
        throw Exception("Feature is not implemented for Sage")
    }

    fun trialBalance(from: LocalDate, to: LocalDate) = scope.later {
        val apiKey = credentials.apiKey
        val companyId = credentials.companyId
        val url = "${environment.path}/TrialBalance/Export?APIKey=$apiKey&companyid=${companyId}"
        val params = mapOf(
            "FromDate" to from.toYYYYMMDD(),
            "ToDate" to to.toYYYYMMDD(),
            "ShowMovement" to true,
            "DisplayReportingGroupDetail" to true,
            "Comparative" to true,
            "CurrencyId" to 1
        )
        val response = client.post(url) {
            authorize()
            setBody(TextContent(text = Mapper.encodeToString(params), contentType = ContentType.Application.Json))
        }
        Mapper.decodeFromString("""{"response":${response.bodyAsText()}}""")["response"] as List<Map<String, *>>
    }

    fun company(id: String) = scope.later {
        val apiKey = credentials.apiKey
        val url = "${environment.path}/Company/Get/$id?APIKey=$apiKey"
        val res = client.get(url) { authorize() }
        Mapper.decodeFromString(res.bodyAsText())
    }

    fun accounts() = scope.later {
        val apiKey = credentials.apiKey
        val companyId = credentials.companyId
        val url = "${environment.path}/Account/GetWithSystemAccounts?APIKey=$apiKey&companyid=${companyId}"
        client.get(url) { authorize() }.bodyAsText()
    }
}