import kotlinext.js.jso
import pimonitor.PiMonitorReactScope

@JsName("_scope")
fun scope(
    builder: SDKConfiguration.() -> Unit
): PiMonitorReactScope = scope(config = jso(builder))