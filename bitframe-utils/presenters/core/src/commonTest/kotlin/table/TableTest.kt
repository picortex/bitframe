package table

import presenters.table.builders.tableOf
import presenters.table.click
import presenters.table.tabulateToConsole
import kotlin.test.Test

class TableTest {
    val processorsTable = tableOf(processors) {
        primaryAction("Add Processor") { println("Add Processor") }
        selectable()
        column("No") { "${it.number}." }
        column("Name") { it.data.name }
        column("Cores") { "${it.data.cores} cores" }
        column("Speed") { "${it.data.clock} GHz" }
        actions("Actions") {
            on("Edit") { println("Editing ${it.data.name}") }
            on("Update") { println("Updating ${it.data.name}") }
        }
    }

    @Test
    fun should_be_able_to_invoke_actions() {
        processorsTable.tabulateToConsole()
        processorsTable.click("Edit", rowNumber = 1)
        processorsTable.click("Edit", rowNumber = 2)
        processorsTable.click("Update", rowNumber = 3)
    }

    @Test
    fun should_be_able_to_selectAll() {
        processorsTable.selectAll()
        processorsTable.tabulateToConsole()
    }

    @Test
    fun should_be_able_to_select_some() {
        processorsTable.select(1)
        processorsTable.select(3)
        processorsTable.tabulateToConsole()
    }
}