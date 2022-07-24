package collections

import expect.expect
import presenters.collections.internal.CollectionPaginator
import presenters.collections.tableOf
import presenters.collections.tabulateToConsole
import kotlin.test.Test

class TableTest {

    fun PersonTable() = tableOf<Person> {
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
        expect(table.currentPageOrNull?.no).toBe(null)

        table.refresh()
        table.tabulateToConsole()

        table.next()
        table.tabulateToConsole()

        table.next()
        table.tabulateToConsole()
    }
}