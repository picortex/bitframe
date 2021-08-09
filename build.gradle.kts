plugins {
    kotlin("multiplatform") version vers.kotlin apply false
    id("tz.co.asoft.library") version vers.asoft.builders apply false
    id("com.bmuschko.docker-java-application") version vers.docker apply false
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

val piMonitorTearDown by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:stopDockerContainer")
    dependsOn(":pi-monitor-server:stopDockerContainer")
}

val piMonitorAcceptanceTest by tasks.creating {
    dependsOn(":pi-monitor-server:clean")
    dependsOn(":pi-monitor-client-browser-react:cleanAllTests")
    dependsOn(":pi-monitor-server:startDockerContainer")
    dependsOn(":pi-monitor-client-browser-react:startDockerContainer")
    dependsOn(":pi-monitor-server:test")
    finalizedBy(":pi-monitor-client-browser-react:allTests")
}