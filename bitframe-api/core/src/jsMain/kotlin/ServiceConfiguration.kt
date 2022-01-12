@file:JsExport

external interface ServiceConfiguration {
    var appId: String
    var url: String?
    var logging: LoggingConfiguration?
}