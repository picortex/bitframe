import bitframe.client.SDKConfiguration
import kotlinext.js.jso
import pimonitor.PiMonitorReactAppScope

@JsName("_scope")
fun scope(
    builder: SDKConfiguration.() -> Unit
): PiMonitorReactAppScope = scope(config = jso(builder))