@file:JsExport

import pimonitor.authentication.signup.SignUpIntent

external interface BusinessData {
    var name: String?
    var email: String?
}

fun stage02Intent(data: BusinessData) = SignUpIntent.Stage02(SignUpIntent.Stage02.Data(data.name, data.email))

fun stage01Intent() = SignUpIntent.Stage01