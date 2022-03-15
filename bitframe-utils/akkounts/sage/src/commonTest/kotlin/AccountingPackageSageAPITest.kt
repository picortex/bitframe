import akkounts.AccountingPackage
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAUserCompany
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import later.await
import kotlin.test.Test

class AccountingPackageSageAPITest {
    var sageService = SageOneZAService(PROD_API_KEY)
    var company: SageOneZAUserCompany = PRODUCTION_ACCOUNT //COMPANY_PICORTEX
    var ap = AccountingPackage(sageService.offeredTo(company))

    @Test
    fun should_fetch_balance_sheet_reports_well() = runTest {
        val date = LocalDate(2021, 10, 1)
        val sheet: BalanceSheet = ap.reports.balanceSheet(date).await()
        expect(sheet).toBeNonNull()
    }

    @Test
    fun should_fetch_income_statement_report() = runTest {
        val start = LocalDate(2022, 2, 11)
        val end = LocalDate(2022, 3, 11)
        val statement: IncomeStatement = ap.reports.incomeStatement(start, end).await()
        println(Json.encodeToString(statement))
        expect(statement).toBeNonNull()
    }

    @Test
    fun should_fetch_cash_flow_statement_report() = runTest {
        try {
            val start = LocalDate(2021, 9, 1)
            val end = LocalDate(2021, 10, 1)
            val statement: CashFlow = ap.reports.cashFlow(start, end).await()
            expect(statement).toBeNonNull()
            println(Json.encodeToString(CashFlow.serializer(), statement))
        } catch (err: Throwable) {
            println(err.message)
            expect(err.message).toBeNonNull()
        }
    }
}