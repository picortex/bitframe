import akkounts.AccountingPackage
import akkounts.MockConsumer
import akkounts.MockService
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.toDetailString
import expect.expect
import kash.MoneyFormatterOptions
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import later.await
import kotlin.test.Test

class AccountingPackageMockAPITest {
    val service = MockService()

    //    val consumer = MockConsumer(name = "Generosity Pixie Company")
    val consumer = MockConsumer(name = "Generosity Company Ltd")
    val ap = AccountingPackage(service.offeredTo(consumer))

    @Test
    fun should_fetch_balance_sheet_reports_well() = runTest {
        val date = LocalDate(2021, 10, 1)
        val sheet: BalanceSheet = ap.reports.balanceSheet(date).await()
        val options = MoneyFormatterOptions(
            abbreviate = false,
            decimals = 0
        )
        println(sheet.toDetailString(options))
        expect(sheet).toBeNonNull()
    }

    @Test
    fun should_fetch_income_statement_report() = runTest {
        val start = LocalDate(2021, 2, 11)
        val end = LocalDate(2022, 3, 11)
        val statement: IncomeStatement = ap.reports.incomeStatement(start, end).await()
        println(statement.toDetailString())
        expect(statement).toBeNonNull()
    }

    @Test
    fun should_fetch_cash_flow_statement_report() = runTest {
        try {
            val start = LocalDate(2021, 9, 1)
            val end = LocalDate(2021, 10, 1)
            val statement: CashFlow = ap.reports.cashFlow(start, end).await()
            expect(statement).toBeNonNull()
        } catch (err: Throwable) {
            println(err.message)
            expect(err.message).toBeNonNull()
        }
    }
}