import kotlinx.css.*
import kotlinx.html.InputType
import react.RBuilder
import react.dom.attrs
import styled.css
import styled.styledDiv
import styled.styledInput
import styled.styledSpan

fun RBuilder.TitleBar(
    title: String = "aSoft Video",
    color: Color
) = styledDiv {
    css {
        display = Display.flex
        backgroundColor = color
        padding(all = 1.em)
    }
    styledSpan {
        css { fontSize = 2.em }
        +title
    }
    styledDiv {
        css {
            paddingLeft = 3.em
            display = Display.flex
        }
        styledInput {
            css {
                width = 50.vw
                outline = Outline.none
                backgroundColor = Color.transparent
                borderRadius = 5.px
                paddingLeft = 1.em
            }
            attrs {
                name = "search"
                type = InputType.text
            }
        }
    }
}