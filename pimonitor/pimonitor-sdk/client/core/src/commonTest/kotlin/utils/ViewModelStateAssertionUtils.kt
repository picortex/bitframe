package utils

import viewmodel.ViewModelStateAssertion
import kotlin.test.assertTrue

fun <S> ViewModelStateAssertion<S>.toContain(vararg states: S) {
    for (state in states) assertTrue(
        "\nViewmodel phases did not pass through phase\n$state"
    ) { value.contains(state) }
}