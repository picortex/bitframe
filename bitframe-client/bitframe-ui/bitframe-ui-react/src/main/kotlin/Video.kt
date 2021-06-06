import kotlinx.css.*
import kotlinx.css.properties.border
import react.RBuilder
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.Video(color: Color) = styledDiv {
    css {
        border(2.px, BorderStyle.solid, color)
        margin(1.em)
        padding(0.5.em)
    }

    styledImg(
        src = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdfPZ3VbusH1U8IZMBiIhZa6yIBvtyErrMUcHv41oOfnpADPqO"
    ) {
        css { width = 100.pct }
    }

    styledDiv {
        css {
            textAlign = TextAlign.center
            fontSize = 1.1.em
        }
        +"Real Steal"
    }
}