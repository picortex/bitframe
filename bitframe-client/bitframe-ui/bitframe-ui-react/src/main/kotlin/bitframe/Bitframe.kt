package bitframe

import bitframe.authentication.login.LoginPage
import react.RBuilder

fun RBuilder.Bitframe(client: BitframeService) {
    LoginPage(client.authentication)
}