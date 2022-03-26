package intergration.reports.cashflow

import akkounts.quickbooks.QuickBooksService
import expect.expect
import kotlinx.datetime.LocalDate
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test


class CashFlowCompanyAUTest {

    private val storage = TokenStorage()

    private val service = QuickBooksService(
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        redirectUrl = REDIRECT_URL,
        storage = storage,
        environment = QuickBooksService.Environment.TEST
    )

    private var company = COMPANY_AU

    @BeforeTest
    fun authorize() = runTest {
        service.refreshTokens(company).await().let {
            println(it)
            company = it
        }
    }

    @Test
    fun should_fetch_cash_flow_statement() = runTest {
        val statement = service.reports.cashFlow(
            company = company,
            start = LocalDate(2020, 7, 1),
            end = LocalDate(2021, 6, 10)
        ).await()
        expect(statement.body.operating.total.amount).toBe(-2085941)
        expect(statement.body.financing.total.amount).toBe(-539084)
        expect(statement.body.investing.total.amount).toBe(0)
        expect(statement.body.opening.amount).toBe(7096271)
        expect(statement.body.closing.amount).toBe(4471246)
    }
}