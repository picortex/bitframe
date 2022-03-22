@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package presenters.cases

import kotlinx.collections.interoperable.List
import presenters.actions.SimpleAction
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@JsExport
sealed interface Case {
    val message: String

    interface Loading : Case {
        val loading: Boolean get() = true // TODO: Check to see if this is still needed in the client side
    }

    interface Failure : Case {
        val cause: Throwable?
        val actions: List<SimpleAction>
        val failure get() = true  // TODO: Check to see if this is still needed in the client side

        companion object {
            @JvmField
            val DEFAULT_MESSAGE = "Unknown error"
        }
    }

    interface Success : Case {
        val actions: List<SimpleAction>
        val success get() = true  // TODO: Check to see if this is still needed in the client side

        companion object {
            @JvmField
            val DEFAULT_MESSAGE = "Success"
        }
    }
}