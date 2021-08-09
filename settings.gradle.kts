pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "bitframe"

include(":bitframe-core")

include(":bitframe-server-core")
project(":bitframe-server-core").projectDir = File("bitframe-server/bitframe-server-core")
include(":bitframe-server-test")
project(":bitframe-server-test").projectDir = File("bitframe-server/bitframe-server-test")
include(":bitframe-server-ktor")
project(":bitframe-server-ktor").projectDir = File("bitframe-server/bitframe-server-ktor")

include(":bitframe-client-sdk-core")
project(":bitframe-client-sdk-core").projectDir = File("bitframe-client/bitframe-client-sdk/bitframe-client-sdk-core")
include(":bitframe-client-sdk-ktor")
project(":bitframe-client-sdk-ktor").projectDir = File("bitframe-client/bitframe-client-sdk/bitframe-client-sdk-ktor")
include(":bitframe-client-sdk-test")
project(":bitframe-client-sdk-test").projectDir = File("bitframe-client/bitframe-client-sdk/bitframe-client-sdk-test")
include(":bitframe-client-viewmodels")
project(":bitframe-client-viewmodels").projectDir = File("bitframe-client/bitframe-client-viewmodels")

include(":bitframe-ui-react")
project(":bitframe-ui-react").projectDir = File("bitframe-client/bitframe-ui/bitframe-ui-react")


include(":access-core")
project(":access-core").projectDir = File("access/access-core")

include(":access-system-core")
project(":access-system-core").projectDir = File("access/access-system/access-system-core")

include(":access-system-client-core")
project(":access-system-client-core").projectDir =
    File("access/access-system/access-system-client/access-system-client-core")
include(":access-system-client-test")
project(":access-system-client-test").projectDir =
    File("access/access-system/access-system-client/access-system-client-test")


//include(":sample-server")
//project(":sample-server").projectDir = File("samples/sample-server")
//include(":sample-client")
//project(":sample-client").projectDir = File("samples/sample-client")

include(":pi-monitor-core")
project(":pi-monitor-core").projectDir = File("pi-monitor/pi-monitor-core")
include(":pi-monitor-server")
project(":pi-monitor-server").projectDir = File("pi-monitor/pi-monitor-server")

include(":pi-monitor-client-test-dsl")
project(":pi-monitor-client-test-dsl").projectDir = File("pi-monitor/pi-monitor-client/pi-monitor-client-test-dsl")

include("pi-monitor-client-browser-react")
project(":pi-monitor-client-browser-react").projectDir =
    File("pi-monitor/pi-monitor-client/pi-monitor-client-browser/pi-monitor-client-browser-react")