@file:JsExport

actual external interface ServiceConfiguration {
    actual var appId: String
    actual var url: String?
    actual var logging: LoggingConfiguration?
}