@file:JsExport

package bitframe.authentication.signup.exports

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