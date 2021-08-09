plugins {
    kotlin("multiplatform") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
}

subprojects {
    group = "com.picortex"
    version = vers.picortex.bitframe
}

val piMonitorReact by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:runJsDebug")
}

val piMonitorServer by tasks.creating {
    dependsOn(":pi-monitor-server:runDebug")
}