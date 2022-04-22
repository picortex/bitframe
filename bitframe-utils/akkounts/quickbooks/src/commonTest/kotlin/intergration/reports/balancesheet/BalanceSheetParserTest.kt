package intergration.reports.balancesheet

import akkounts.quickbooks.reports.balancesheet.BalanceSheetParser
import datetime.Date
import datetime.toSimpleDateTime
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
        expect(sheet.header.endOf).toBe(Date(2021, 6, 10))
    }

    @Test
    fun should_parse_assets_correctly() {
        expect(sheet.body.assets.current.total.amount).toBe(7791246)
        expect(sheet.body.assets.fixed.total.amount).toBe(235004)
        expect(sheet.body.assets.total.amount).toBe(8026250)
    }

    @Test
    fun should_parse_equity_correctly() {
        expect(sheet.body.equity.total.amount).toBe(3128712)
    }

    @Test
    fun should_parse_liabilities_correctly() {
        expect(sheet.body.liabilities.current.total.amount).toBe(387326)
        expect(sheet.body.liabilities.longTerm.total.amount).toBe(4510212)
    }
}