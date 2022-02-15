package bitframe.client.configurators

import kotlin.js.JsExport

@JsExport
interface SdkConfigurator : ApiConfigurator {
    var transitionTime: Int?
    var recoveryTime: Int?
}