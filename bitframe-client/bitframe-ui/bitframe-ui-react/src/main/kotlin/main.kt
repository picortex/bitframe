import kotlinx.browser.document
import kotlinx.css.*
import react.dom.render
import styled.css
import styled.styledDiv

fun main() {
    render(document.getElementById("root")) {
        val orange = rgb(241, 151, 91)
        TitleBar(
            title = "Joh Vidz",
            color = orange
        )
        styledDiv {
            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns("1fr 1fr 1fr 1fr")
            }
            for (v in 1..8) Video(color = orange)
        }
    }
}