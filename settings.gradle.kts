pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
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
include(":bitframe-annotations")
include(":bitframe-processor")

include(":bitframe-server-framework-core")
project(":bitframe-server-framework-core").projectDir = File("bitframe-server/frameworks/core")
include(":bitframe-server-framework-test")
project(":bitframe-server-framework-test").projectDir = File("bitframe-server/frameworks/test")
include(":bitframe-server-framework-ktor")
project(":bitframe-server-framework-ktor").projectDir = File("bitframe-server/frameworks/ktor")

include(":bitframe-server-dao-inmemory")
project(":bitframe-server-dao-inmemory").projectDir = File("bitframe-server/daos/inmemory")

include(":bitframe-client-sdk-core")
project(":bitframe-client-sdk-core").projectDir = File("bitframe-client/sdks/core")
include(":bitframe-client-sdk-ktor")
project(":bitframe-client-sdk-ktor").projectDir = File("bitframe-client/sdks/ktor")
include(":bitframe-client-sdk-test")
project(":bitframe-client-sdk-test").projectDir = File("bitframe-client/sdks/test")
include(":bitframe-client-viewmodels")
project(":bitframe-client-viewmodels").projectDir = File("bitframe-client/viewmodels")

include(":bitframe-ui-react")
project(":bitframe-ui-react").projectDir = File("bitframe-client/ui/react")


include(":users-core")
project(":users-core").projectDir = File("users/users-core")
include(":users-server-services-core")
project(":users-server-services-core").projectDir = File("users/users-server/services/core")
include(":users-server-dao-core")
project(":users-server-dao-core").projectDir = File("users/users-server/daos/core")
include(":users-server-dao-inmemory")
project(":users-server-dao-inmemory").projectDir = File("users/users-server/daos/inmemory")


include(":access-core")
project(":access-core").projectDir = File("access/access-core")

include(":access-system-core")
project(":access-system-core").projectDir = File("access/access-system/access-system-core")
//
//include(":access-system-server-core")
//project(":access-system-server-core").projectDir =
//    File("access/access-system/access-system-server/access-system-server-core")

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

include(":pi-monitor")
include(":pi-monitor-core")
project(":pi-monitor-core").projectDir = File("pi-monitor/pi-monitor-core")
include(":pi-monitor-server")
project(":pi-monitor-server").projectDir = File("pi-monitor/pi-monitor-server")

include(":pi-monitor-client-sdk-core")
project(":pi-monitor-client-sdk-core").projectDir = File("pi-monitor/pi-monitor-client/sdks/core")

include(":pi-monitor-client-sdk-full")
project(":pi-monitor-client-sdk-full").projectDir = File("pi-monitor/pi-monitor-client/sdks/full")

include(":pi-monitor-client-test")
project(":pi-monitor-client-test").projectDir = File("pi-monitor/pi-monitor-client/test")

include("pi-monitor-client-browser-react")
project(":pi-monitor-client-browser-react").projectDir = File("pi-monitor/pi-monitor-client/browser/react")