package bitframe.landing

import bitframe.SignInPageRoute
import bitframe.SignUpPageRoute
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import react.router.useNavigate
import reakt.*
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