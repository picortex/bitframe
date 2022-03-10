package unit

import akkounts.accounts.Account
import akkounts.accounts.types.Asset
import akkounts.utils.unset
import expect.expect
import expect.toBe
import kash.Currency
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class AccountSerializationTest {
    @Test
    fun can_serialize_an_account() {
        val account = Account(
            id = unset,
            name = "Test Account",
            type = Asset.Current.Inventory,
            currency = Currency.TZS
        )
        val json = Json.encodeToString(account)
        println(json)
        expect(json).toBe<String>()
    }
}