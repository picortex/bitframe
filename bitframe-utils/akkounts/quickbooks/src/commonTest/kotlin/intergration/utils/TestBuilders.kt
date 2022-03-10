package intergration.utils

import akkounts.QuickBooksCompany
import akkounts.quickbooks.QuickBooksService
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest

fun quickBooksServiceTest(
    company: QuickBooksCompany = COMPANY_AU,
    block: suspend QuickBooksService.(company: QuickBooksCompany) -> Unit
) = runTest {
    val storage = TokenStorage()
    val service = QuickBooksService(
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        redirectUrl = REDIRECT_URL,
        storage = storage,
        environment = QuickBooksService.Environment.TEST
    )
    service.block(service.refreshTokens(company).await().also { println(it) })
}