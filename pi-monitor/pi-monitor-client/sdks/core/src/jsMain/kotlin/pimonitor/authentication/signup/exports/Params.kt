@file:JsExport

package pimonitor.authentication.signup.exports

@Deprecated("In favour of RegisterBusinessParams")
external interface RegisterOrganisationParams {
    var name: String
    var email: String
}

external interface RegisterIndividualParams {
    var name: String
    var email: String
    var password: String
}

external interface RegisterBusinessParams {
    var businessName: String
    var individualName: String
    var individualEmail: String
    var password: String
}