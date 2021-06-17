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

include(":bitframe-server-core")
project(":bitframe-server-core").projectDir = File("bitframe-server/bitframe-server-core")
include(":bitframe-server-test")
project(":bitframe-server-test").projectDir = File("bitframe-server/bitframe-server-test")
include(":bitframe-server-ktor")
project(":bitframe-server-ktor").projectDir = File("bitframe-server/bitframe-server-ktor")

include(":bitframe-ui-react")
project(":bitframe-ui-react").projectDir = File("bitframe-client/bitframe-ui/bitframe-ui-react")

include(":sample-server")
project(":sample-server").projectDir = File("samples/sample-server")
include(":sample-client")
project(":sample-client").projectDir = File("samples/sample-client")
