package bitframe.client.landing

import bitframe.client.SignInPageRoute
import bitframe.client.SignUpPageRoute
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import react.router.useNavigate
import reakt.ContainedButton
import reakt.FlexBox
import reakt.Grid
import reakt.centerContent
import styled.css

private external interface LandingPageProps : Props {
    var version: String
}

private val LandingPage = fc<LandingPageProps> { props ->
    val navigate = useNavigate()
    Grid {
        css {
            height = 100.vh
            centerContent()
        }
        FlexBox(direction = FlexDirection.column) {
            css { gap = 1.em }
            +"Welcome to Bitframe ${props.version}"
            Grid(cols = "1fr 1fr") {
                ContainedButton("Sing Up") { navigate(SignUpPageRoute) }
                ContainedButton("Sign In") { navigate(SignInPageRoute) }
            }
        }
    }
}

fun RBuilder.LandingPage(version: String) = LandingPage {
    attrs.version = version
}