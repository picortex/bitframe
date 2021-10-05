package testing.pimonitor

import testing.ContainerTest
import testing.TestMode

val APP_ID
    get() = when (ContainerTest.mode) {
        TestMode.DEV -> "dev-app-1"
        TestMode.CI -> "ci-app-1"
        TestMode.CD -> "cd-app-1"
    }