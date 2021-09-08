package utils

import viewmodel.ViewModelStateAssertion
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private fun <E> Collection<E>.hasSameValuesAs(other: Collection<E>): Boolean {
    if (size != other.size) return false
    var different = false
    zip(other).forEach { (first, second) ->
        println(first == second)
        different = first != second
        if (different) return false
    }
    return !different
//    return containsAll(other)
}

fun <S> ViewModelStateAssertion<S>.toPassThrough(vararg states: S) {
    assertTrue(
        value.toList().hasSameValuesAs(states.toList()),
//        states.toList(), value,
//        states.toList().containsAll(value),
        """
            
        Expected ViewModel State Path : ${states.toList()}
        Actual ViewModel States Path  : $value
        ==================================
        
        """.trimIndent()
    )
}