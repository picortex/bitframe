@file:JsExport

package pimonitor.api.businesses

actual external interface RawCreateBusinessFormParams {
    actual var businessName: String
    actual var contactName: String
    actual var contactEmail: String
}