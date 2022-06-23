package intergration.customers

import akkounts.quickbooks.QuickBooksService
import akkounts.quickbooks.customers.QuickBooksCustomerParams
import akkounts.quickbooks.customers.QuickBooksCustomersService
import expect.expect
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

class CustomerServiceTest {
    private val storage = TokenStorage()
    private val service = QuickBooksCustomersService(
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
    fun should_fetch_customers() = runTest {
        val customers = service.all(company).await()
        println(customers)
        expectCollection(customers).toContainElements()
    }

    @Test
    fun can_create_a_new_customer() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = QuickBooksCustomerParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val customer = service.create(company, params).await()
        expect(customer.id).toBe<String>()
    }

    @Test
    fun can_get_a_customer_with_display_name() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = QuickBooksCustomerParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val createdCustomer = service.create(company, params).await()
        val customer = service.search(company, displayName = createdCustomer.displayName).await()
        println(customer)
        expect(customer.firstOrNull()).toBeNonNull()
    }

    @Test
    fun should_return_an_empty_list_when_searching_a_missing_customer() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val customerList = service.search(company, displayName = "Invalid customer -$now").await()
        expectCollection(customerList).toBeEmpty()
    }

    @Test
    fun should_be_able_to_get_or_create_a_customer() = runTest {
        val now = Clock.System.now().toEpochMilliseconds()
        val params = QuickBooksCustomerParams(
            givenName = "Anderson - $now",
            displayName = "Anderson - $now"
        )
        val createdCustomer = service.getOrCreate(company, params).await()
        val customer = service.search(company, displayName = createdCustomer.displayName).await()
        println(customer)
        expect(customer.firstOrNull()).toBeNonNull()
    }
}