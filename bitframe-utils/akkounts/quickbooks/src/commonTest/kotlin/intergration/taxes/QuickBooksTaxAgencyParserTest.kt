package intergration.taxes

import akkounts.quickbooks.taxes.QuickBooksTaxAgencyParser
import expect.expect
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class QuickBooksTaxAgencyParserTest {
    val rawJson = AGENCIES_JSON
    val response = Mapper.decodeFromString(rawJson)
    val parser = QuickBooksTaxAgencyParser()

    @Test
    fun should_contain_four_agencies() {
        val agencies = parser.parse(response)
        expect(agencies.size).toBe(4)
    }

    @Test
    fun should_contain_anderson_revenue_authority() {
        val agency = parser.parse(response).find { it.name == "AndersonRevenueAuthority" }
        expect(agency).toBeNonNull()
    }
}