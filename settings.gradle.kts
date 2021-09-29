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

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "bitframe"

include(":bitframe-core")
include(":bitframe-presenters")

includeSubs(base = "bitframe-annotations", path = "bitframe-annotations", "core", "processor")

includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core")

includeSubs(base = "bitframe-authentication", path = "bitframe-authentication", "core")

includeSubs(base = "bitframe-authentication-service", path = "bitframe-authentication/services", "core", "test")

includeSubs(base = "bitframe-authentication-dao", path = "bitframe-authentication/daos", "core", "inmemory")

includeSubs(base = "bitframe-server-dao", path = "bitframe-server/daos", "core", "inmemory")

includeSubs(base = "bitframe-server-framework", path = "bitframe-server/frameworks", "core", "test", "ktor")

includeSubs(base = "bitframe-client-sdk", path = "bitframe-client/sdks", "core", "test", "ktor")

includeSubs(base = "bitframe-client", path = "bitframe-client", "viewmodels")

includeSubs(base = "bitframe-ui", path = "bitframe-client/ui", "react")

includeRoot(name = "users-core", path = "users/users-core")

includeSubs(base = "users-server-services", path = "users/users-server/services", "core")

includeSubs(base = "users-server-dao", path = "users/users-server/daos", "core", "inmemory")

include(":pi-monitor")

includeRoot(name = "pi-monitor-core", path = "pi-monitor/pi-monitor-core")

includeRoot(name = "pi-monitor-server", path = "pi-monitor/pi-monitor-server")

includeSubs(base = "pi-monitor-client-sdk", path = "pi-monitor/pi-monitor-client/sdks", "core", "full")

includeSubs(base = "pi-monitor-client", path = "pi-monitor/pi-monitor-client", "test")

includeSubs(base = "pi-monitor-test", path = "pi-monitor/pi-monitor-test", "containers")

includeSubs(base = "pi-monitor-client-browser", path = "pi-monitor/pi-monitor-client/browser", "react")