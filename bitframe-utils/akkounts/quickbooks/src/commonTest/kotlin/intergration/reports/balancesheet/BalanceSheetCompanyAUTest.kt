package intergration.reports.balancesheet

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

class BalanceSheetCompanyAUTest {
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
    fun should_get_the_correct_balance_sheet() = runTest {
        val sheet = service.reports.balanceSheet(company, LocalDate(2021, 6, 10)).await()
        expect(sheet.body.assets.current.total).toBe(7791246)
        expect(sheet.body.assets.fixed.total).toBe(235004)
        expect(sheet.body.assets.total).toBe(8026250)

        expect(sheet.body.liabilities.current.total).toBe(387326)
        expect(sheet.body.liabilities.longTerm.total).toBe(4510212)

        expect(sheet.body.equity.total).toBe(3128712)
    }
}