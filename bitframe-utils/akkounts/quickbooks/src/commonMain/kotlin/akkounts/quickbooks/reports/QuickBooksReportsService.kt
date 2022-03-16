package akkounts.quickbooks.reports

import akkounts.*
import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.Service
import akkounts.quickbooks.reports.balancesheet.BalanceSheetParser
import akkounts.quickbooks.reports.cashflow.CashFlowParser
import akkounts.quickbooks.reports.incomestatement.IncomeStatementParser
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.utils.toYYYYMMDD
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.*
import kotlinx.serialization.mapper.Mapper
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmOverloads

class QuickBooksReportsService @JvmOverloads constructor(
    override val clientId: String,
    override val clientSecret: String,
    override val redirectUrl: String,
    override val storage: QuickBooksTokenStorage,
    override val environment: QuickBooksService.Environment = QuickBooksService.Environment.PROD,
    override val version: String = "v3",
    override val client: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : Service {
    fun incomeStatement(
        company: QuickBooksCompany,
        start: LocalDate,
        end: LocalDate
    ): Later<IncomeStatement> = scope.later {
        val comp = refreshTokens(company).await()
        val paramMap = mapOf(
            "start_date" to start.toYYYYMMDD('-'),
            "end_date" to end.toYYYYMMDD('-'),
            "minorversion" to 12
        )
        val params = paramMap.entries.joinToString("&") { (key, value) -> "$key=$value" }
        val url = "${environment.url}/$version/company/${comp.realmId}/reports/ProfitAndLoss?$params"
        val res = client.get(url) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${comp.accessToken}")
        }
        val parser = IncomeStatementParser(Mapper.decodeFromString(res.bodyAsText()))
        parser.parse()
    }

    fun balanceSheet(company: QuickBooksCompany, at: LocalDate): Later<BalanceSheet> = scope.later {
        val comp = refreshTokens(company).await()
        val end = at
        val start = end - DatePeriod(0, 0, 1)
        val paramMap = mapOf(
            "start_date" to start.toYYYYMMDD('-'),
            "end_date" to end.toYYYYMMDD('-'),
            "minorversion" to 12
        )
        val params = paramMap.entries.joinToString("&") { (key, value) -> "$key=$value" }
        val url = "${environment.url}/$version/company/${comp.realmId}/reports/BalanceSheet?$params"
        val res = client.get(url) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${comp.accessToken}")
        }
        val parser = BalanceSheetParser(Mapper.decodeFromString(res.bodyAsText()))
        parser.parse()
    }

    fun cashFlow(company: QuickBooksCompany, start: LocalDate, end: LocalDate): Later<CashFlow> = scope.later {
        val comp = refreshTokens(company).await()
        val paramMap = mapOf(
            "start_date" to start.toYYYYMMDD('-'),
            "end_date" to end.toYYYYMMDD('-'),
            "minorversion" to 12
        )
        val params = paramMap.entries.joinToString("&") { (key, value) -> "$key=$value" }
        val url = "${environment.url}/$version/company/${comp.realmId}/reports/CashFlow?$params"
        val res = client.get(url) {
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${comp.accessToken}")
        }
        val parser = CashFlowParser(Mapper.decodeFromString(res.bodyAsText()))
        parser.parse()
    }
}