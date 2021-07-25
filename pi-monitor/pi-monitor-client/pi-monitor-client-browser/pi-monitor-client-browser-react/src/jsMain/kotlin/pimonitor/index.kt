package pimonitor

import bitframe.Bitframe
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.extensions.text
import kotlinx.html.InputType
import kotlinx.html.InputType.password
import reakt.Form
import reakt.TextInput
import reakt.setContent
import styled.styledH1

fun main() = document.getElementById("root").setContent {
    Bitframe()
}