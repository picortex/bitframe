package intergration.vendors

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.vendors.VendorParams
import akkounts.quickbooks.vendors.QuickBooksVendorsService
import expect.*
import kotlinx.datetime.Clock
import later.await
import intergration.TokenStorage
import intergration.auth.CLIENT_ID
import intergration.auth.CLIENT_SECRET
import intergration.auth.COMPANY_AU
import intergration.auth.REDIRECT_URL
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class VendorsServiceTest {
    private val storage = TokenStorage()
    private val service = QuickBooksVendorsService(
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        redirectUrl = REDIRECT_URL,
        storage = storage,
        environment = QuickBooksService.Environment.TEST
    )

    private var company = COMPANY_AU

    @BeforeTest
    fun authorize() = runTest {
        company = service.refreshTokens(company).await()
        company
    }

    @Test
    fun should_fetch_vendors() = runTest {
        val vendors = service.all(company).await()
        println(vendors)
        expectCollection(vendors).toContainElements()
    }

    @Test
    fun can_create_a_new_vendor() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = VendorParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val vendor = service.create(company, params).await()
        expect(vendor.id).toBe<String>()
    }

    @Test
    fun can_get_a_vendor_with_display_name() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = VendorParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val createdvendor = service.create(company, params).await()
        val vendor = service.search(company, displayName = createdvendor.displayName).await()
        println(vendor)
        expect(vendor.firstOrNull()).toBeNonNull()
    }

    @Test
    fun should_return_an_empty_list_when_searching_a_missing_vendor() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val vendorList = service.search(company, displayName = "Invalid vendor -$now").await()
        expectCollection(vendorList).toBeEmpty()
    }

    @Test
    fun should_be_able_to_get_or_create_a_vendor() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = VendorParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val createdVendor = service.getOrCreate(company, params).await()
        val vendor = service.search(company, displayName = createdVendor.displayName).await()
        println(vendor)
        expect(vendor.firstOrNull()).toBeNonNull()
    }
}