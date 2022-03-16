package intergration.reports.incomestatement

import akkounts.quickbooks.QuickBooksService
import expect.expect
import kotlinx.datetime.LocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class IncomeStatementCompanyAUTest {
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
    fun should_retrieve_income_statements() = runTest {
        val statement = service.reports.incomeStatement(
            company = company,
            start = LocalDate(2020, 7, 1),
            end = LocalDate(2021, 6, 8)
        ).await()
        println(Json.encodeToString(statement))
        expect(statement.body.income.total).toBe(5517117)
        expect(statement.body.expenses.total).toBe(1852212)
    }
}