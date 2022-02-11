package pimonitor.api.businesses

expect interface RawCreateBusinessFormParams {
    var businessName: String
    var contactName: String
    var contactEmail: String
}