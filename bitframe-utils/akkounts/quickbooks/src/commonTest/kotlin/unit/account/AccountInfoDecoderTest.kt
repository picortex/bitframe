package unit.account

import akkounts.accounts.types.Asset
import akkounts.quickbooks.accounts.AccountInfoDecoder
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AccountInfoDecoderTest {
    private val decoder = AccountInfoDecoder

    @Test
    fun should_decode_from_inventory_account_type_to_quickbooks_info() {
        val decoded = decoder.decode("Inventory")
        val actual = Asset.Current.Inventory
        expect(decoded).toBe(actual)
    }

    @Test
    fun should_decode_from_other_current_assets_to() {
        val decoded = decoder.decode("Other Current Asset")
        val actual = Asset.Current.Other
        expect(decoded).toBe(actual)
    }

    @Test
    fun should_throw_a_descriptive_error_when_it_fails_to_decode() = runTest {
        val error = expectFailure { decoder.decode("Yagara") }
        expect(error.message).toBe("Failed to decode account of type Yagara")
    }
}