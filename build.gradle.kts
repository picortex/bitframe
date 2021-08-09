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

val piMonitorAcceptanceTestSetup by tasks.creating {
    dependsOn(":pi-monitor-server:acceptanceTestSetup")
    finalizedBy(":pi-monitor-client-browser-react:acceptanceTestSetup")
}

val piMonitorAcceptanceTestTearDown by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:acceptanceTestTearDown")
    finalizedBy(":pi-monitor-server:acceptanceTestTearDown")
}

val piMonitorAcceptanceTest by tasks.creating {
    dependsOn(piMonitorAcceptanceTestSetup)
    finalizedBy(piMonitorAcceptanceTestTearDown)
}