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
