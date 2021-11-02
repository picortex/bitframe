package unit.table

import bitframe.presenters.collections.tableOf
import kotlin.test.Test

class TableTest {
    data class Processor(val name: String, val cores: Int, val clock: Double)

    val processors = listOf(
        Processor("Intel core i5", 4, 2.5),
        Processor("AMD  Ryzen 6", 4, 3.0),
        Processor("AMD  Ryzen 4", 2, 3.4),
        Processor("Intel Xeon 3", 8, 4.3)
    )

    val processorsTable = tableOf(processors) {
        selectable()
        column("No") { "${it.rowNumber}." }
        column("Name") { it.data.name }
        column("Cores") { "${it.data.cores} cores" }
        column("Speed") { "${it.data.clock} GHz" }
        actions("Actions") {
            action("Edit") { println("Editing ${it.data.name}") }
            action("Update") { println("Updating ${it.data.name}") }
        }
    }

    @Test
    fun should_be_able_to_invoke_actions() {
        print(processorsTable)
        processorsTable.click("Edit", rowNumber = 1)
        processorsTable.click("Edit", rowNumber = 2)
        processorsTable.click("Update", rowNumber = 3)
    }

    @Test
    fun should_be_able_to_selectAll() {
        processorsTable.selectAll()
        print(processorsTable)
    }

    @Test
    fun should_be_able_to_select_some() {
        processorsTable.select(1)
        processorsTable.select(3)
        print(processorsTable)
    }
}