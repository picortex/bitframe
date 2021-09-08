package bitframe.landing

import bitframe.SignInPageRoute
import bitframe.SignUpPageRoute
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import reakt.*
import styled.css

private external interface LandingPageProps : Props {
    var version: String
}

private val LandingPage = fc<LandingPageProps> { props ->
    Grid {
        css {
            height = 100.vh
            centerContent()
        }
        FlexBox(direction = FlexDirection.column) {
            css { gap = 1.em }
            +"Welcome to Bitframe ${props.version}"
            Grid(cols = "1fr 1fr") {
                ContainedButton("Sing Up") { props.withRouter.history.push(SignUpPageRoute) }
                ContainedButton("Sign In") { props.withRouter.history.push(SignInPageRoute) }
            }
        }
    }
}

fun RBuilder.LandingPage(version: String) = child(withRouter(LandingPage)) {
    attrs.version = version
}