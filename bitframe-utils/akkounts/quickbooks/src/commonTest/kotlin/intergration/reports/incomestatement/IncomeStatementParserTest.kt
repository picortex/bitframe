package intergration.reports.incomestatement

import akkounts.quickbooks.reports.incomestatement.IncomeStatementParser
import datetime.Date
import datetime.toSimpleDateTime
import expect.expect
import kotlinx.datetime.LocalDate
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class IncomeStatementParserTest {

    val rawJson = INCOME_STATEMENT_JSON

    @Test
    fun should_parse_dates_correctly() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val from = Date(2020, 7, 1)
        val to = Date(2021, 6, 8)
        expect(statement.header.start).toBe(from)
        expect(statement.header.end).toBe(to)
    }

    @Test
    fun should_parse_income_categories_correctly() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val income = statement.body.revenue.operating
        expect(income.items.first { it.details == "Markup" }.value.centsAsInt).toBe(99500)
        expect(income.total.centsAsInt).toBe(5517117)
    }

    @Test
    fun should_parse_cost_of_goods_sold_too() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val cogs = statement.body.costOfRevenue
        expect(cogs.items.first { it.details == "Cost of sales" }.value.centsAsInt).toBe(1054348)
        expect(cogs.total.centsAsInt).toBe(4063873)
    }

    @Test
    fun should_parse_expenses_well() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val expenses = statement.body.expenses.operating
        expect(expenses.items.first { it.details == "Utilities - Water" }.value.centsAsInt).toBe(76651)
        expect(expenses.total.centsAsInt).toBe(1850121)
    }
}