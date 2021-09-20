package pimonitor.authentication.signup

import color.invoke
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.RBuilder
import reakt.AspectRationDiv
import reakt.Grid
import reakt.centerContent
import styled.css
import styled.styledDiv
import styled.styledH2
import theme.clazz

internal fun RBuilder.SelectRegistrationType(
    onIndividualClicked: () -> Unit,
    onOrganisationClicked: () -> Unit
) = styledDiv {
    css {
        centerContent()
        margin(20.pct, 40.pct)
    }

    Grid { theme ->
        css { gridColumn = GridColumn("1/3") }
        styledH2 {
            css { +theme.text.h2.clazz }
            +"Register As"
        }
    }

    Grid(cols = "1fr 1fr") { theme ->
        listOf(
            "Individual" to onIndividualClicked,
            "Organisation" to onOrganisationClicked
        ).forEach { (group, handler) ->
            AspectRationDiv {
                css {
                    backgroundColor = theme.color.primary()
                    color = theme.color.onPrimary()
                    hover {
                        backgroundColor = theme.color.primaryVariant()
                        color = theme.color.onPrimary()
                    }
                    cursor = Cursor.pointer
                }
                attrs.onClickFunction = { handler() }
                styledDiv { +group }
            }
        }
    }
}