package intergration.packager.utils

import akkounts.QuickBooksCompany
import akkounts.provider.AccountingPackager
import akkounts.quickbooks.QuickBooksPackager
import akkounts.quickbooks.QuickBooksService
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest

fun quickBooksPackagerTest(
    company: QuickBooksCompany = COMPANY_AU,
    block: suspend AccountingPackager.() -> Unit
) = runTest {
    val storage = TokenStorage()
    val service = QuickBooksService(
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        redirectUrl = REDIRECT_URL,
        storage = storage,
        environment = QuickBooksService.Environment.TEST
    )
    val packager = service.offeredTo(service.refreshTokens(company).await().also { println(it) })
    packager.block()
}