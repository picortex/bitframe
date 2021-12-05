package mailer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface MockMailerConfig {
    val printToConsole: Boolean
    val separator: String
    val simulationTime: Long
    val scope: CoroutineScope

    companion object {
        @JvmField
        val DEFAULT_PRINT_TO_CONSOLE = true

        @JvmField
        val DEFAULT_SIMULATION_TIME = 0L

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())

        @JvmField
        val DEFAULT_SEPERATOR = "- - - - - - - - - - - - -"

        @JvmSynthetic
        operator fun invoke(
            printToConsole: Boolean = DEFAULT_PRINT_TO_CONSOLE,
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            separator: String = DEFAULT_SEPERATOR,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : MockMailerConfig {
            override val printToConsole: Boolean = printToConsole
            override val simulationTime: Long = simulationTime
            override val separator: String = separator
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            printToConsole: Boolean = DEFAULT_PRINT_TO_CONSOLE,
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            separator: String = DEFAULT_SEPERATOR,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(printToConsole, simulationTime, separator, scope)
    }
}