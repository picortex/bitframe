package intergration.reports.incomestatement

import akkounts.quickbooks.reports.incomestatement.IncomeStatementParser
import expect.expect
import kotlinx.datetime.LocalDate
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class IncomeStatementParserTest {

    val rawJson = INCOME_STATEMENT_JSON

    @Test
    fun date_instances_should_pass_the_to_equals_test() {
        expect(LocalDate(2021, 1, 1)).toBe(LocalDate(2021, 1, 1))
    }

    @Test
    fun should_parse_dates_correctly() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val from = LocalDate(2020, 7, 1)
        val to = LocalDate(2021, 6, 8)
        expect(statement.header.start).toBe(from)
        expect(statement.header.end).toBe(to)
    }

    @Test
    fun should_parse_income_categories_correctly() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val income = statement.body.income
        expect(income.items.first { it.details == "Markup" }.amount).toBe(99500)
        expect(income.total).toBe(5517117)
    }

    @Test
    fun should_parse_cost_of_goods_sold_too() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val cogs = statement.body.costOfSales
        expect(cogs.items.first { it.details == "Cost of sales" }.amount).toBe(1054348)
        expect(cogs.total).toBe(4063873)
    }

    @Test
    fun should_parse_expenses_well() {
        val statement = IncomeStatementParser(Mapper.decodeFromString(rawJson)).parse()
        val expenses = statement.body.expenses
        expect(expenses.items.first { it.details == "Utilities - Water" }.amount).toBe(76651)
        expect(expenses.total).toBe(1850121)
    }
}