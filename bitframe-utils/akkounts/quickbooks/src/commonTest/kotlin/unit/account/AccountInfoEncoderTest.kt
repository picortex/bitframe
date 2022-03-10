package unit.account

import akkounts.accounts.types.Asset
import akkounts.quickbooks.accounts.AccountInfoEncoder
import akkounts.quickbooks.accounts.QuickBooksAccountParams
import expect.expect
import kotlin.test.Test

class AccountInfoEncoderTest {
    private val encoder = AccountInfoEncoder

    @Test
    fun should_encode_from_inventory_account_type_to_quickbooks_info() {
        val encoded = encoder.encode(Asset.Current.Inventory)
        val actual = QuickBooksAccountParams.Info.SubType("Inventory")
        expect(encoded).toBe(actual)
    }
}