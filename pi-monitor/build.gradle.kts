val runWeb by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:runJsDebug")
}

val runServer by tasks.creating {
    dependsOn(":pi-monitor-server:runDebug")
}

val acceptanceTestSetup by tasks.creating {
    dependsOn(":pi-monitor-server:acceptanceTestSetup")
    finalizedBy(":pi-monitor-client-browser-react:acceptanceTestSetup")
}

val acceptanceTestTearDown by tasks.creating {
    dependsOn(":pi-monitor-client-browser-react:acceptanceTestTearDown")
    finalizedBy(":pi-monitor-server:acceptanceTestTearDown")
}

val acceptanceTest by tasks.creating {
    dependsOn(acceptanceTestSetup)
    finalizedBy(acceptanceTestTearDown)
}