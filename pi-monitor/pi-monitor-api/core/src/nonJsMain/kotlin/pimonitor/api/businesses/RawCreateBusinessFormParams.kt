package pimonitor.api.businesses

actual interface RawCreateBusinessFormParams {
    actual var businessName: String
    actual var contactName: String
    actual var contactEmail: String
}