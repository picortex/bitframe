package intergration.reports.balancesheet

import akkounts.quickbooks.reports.balancesheet.BalanceSheetParser
import expect.expect
import expect.toBe
import kotlinx.datetime.LocalDate
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class BalanceSheetParserTest {

    val rawJson = BALANCE_SHEET_JSON

    val parser = BalanceSheetParser(Mapper.decodeFromString(rawJson))
    val sheet get() = parser.parse()

    @Test
    fun should_be_able_to_read_the_json() {
        expect(rawJson).toBe<String>()
    }

    @Test
    fun should_parse_the_date_as_required() {
        expect(sheet.header.endOf).toBe(LocalDate(2021, 6, 10))
    }

    @Test
    fun should_parse_assets_correctly() {
        expect(sheet.body.assets.current.total).toBe(7791246)
        expect(sheet.body.assets.fixed.total).toBe(235004)
        expect(sheet.body.assets.total).toBe(8026250)
    }

    @Test
    fun should_parse_equity_correctly() {
        expect(sheet.body.equity.total).toBe(3128712)
    }

    @Test
    fun should_parse_liabilities_correctly() {
        expect(sheet.body.liabilities.current.total).toBe(387326)
        expect(sheet.body.liabilities.longTerm.total).toBe(4510212)
    }
}