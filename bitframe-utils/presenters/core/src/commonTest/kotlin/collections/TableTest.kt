package collections

import expect.expect
import presenters.collections.internal.CollectionPaginator
import presenters.collections.internal.SelectorImpl
import presenters.collections.tableOf
import presenters.collections.tabulateToConsole
import viewmodel.ViewModelConfig
import kotlin.test.Test

class TableTest {

    fun PersonTable() = tableOf<Person> {
        selectable()
        column("No") { it.number.toString() }
        column("name") { it.item.name }
        column("age") { it.item.age.toString() }
    }

    @Test
    fun should_have_a_paginator() {
        val table = PersonTable()
        table.tabulateToConsole()
    }

    @Test
    fun can_be_assigned_a_paginator() {
        val table = PersonTable().map(
            paginator = CollectionPaginator(Person.List)
        )
        table.tabulateToConsole()
        expect(table.currentPageOrNull?.number).toBe(null)

        table.refresh()
        table.tabulateToConsole()

        table.loadNextPage()
        table.tabulateToConsole()

        table.loadNextPage()
        table.tabulateToConsole()
    }

    @Test
    fun should_be_able_to_select_table_items() {
        val paginator = CollectionPaginator(Person.List)
        val selector = SelectorImpl(paginator, ViewModelConfig())
        val table = PersonTable().map(
            paginator = paginator
        )
        table.loadFirstPage()
        table.tabulateToConsole()

        table.select(row = 1)
        selector.select(row = 1)
        table.tabulateToConsole()
        expect(selector.isRowSelected(row = 1)).toBe(true, "Explicit selector failed to select")
        expect(table.isRowSelected(row = 1)).toBe(true, "Implicit selector failed to select")
    }

    @Test
    fun should_be_able_to_select_the_whole_current_page() {
        val paginator = CollectionPaginator(Person.List)
        val table = PersonTable().map(
            paginator = paginator
        )
        table.loadFirstPage()
        table.tabulateToConsole()

        table.selectAllItemsInTheCurrentPage()
        table.tabulateToConsole()
        expect(table.isRowSelected(row = 1)).toBe(true, "Implicit selector failed to select")
    }
}