@file:JsExport

package pimonitor.authentication.signup.exports

external interface OrganisationParams {
    var name: String
    var email: String
}

external interface IndividualParams : OrganisationParams {
    var password: String
}