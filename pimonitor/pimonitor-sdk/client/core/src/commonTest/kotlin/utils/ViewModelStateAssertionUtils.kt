package utils

import viewmodel.ViewModelStateAssertion
import kotlin.test.assertTrue

fun <S> ViewModelStateAssertion<S>.toContain(vararg states: S) {
    for (state in states) assertTrue(
        "Viewmodel phases did not pass through phase\n$state\n\nActual phases are\n${value.joinToString(separator = "\n")}"
    ) { value.contains(state) }
}