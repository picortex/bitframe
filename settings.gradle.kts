pluginManagement {
    enableFeaturePreview("VERSION_CATALOGS")
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map { it.nameWithoutExtension }.forEach {
                create(it) { from(files("gradle/versions/$it.toml")) }
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


// <Bitframe Utils>
includeSubs("mailer", "bitframe-utils/mailer", "api", "mock", "smtp")

includeRoot("templater", "bitframe-utils/templater")

includeSubs(base = "bitframe-events", path = "bitframe-utils/events", "core", "inmemory", "react")

includeSubs(base = "bitframe-events", path = "bitframe-utils/events", "core", "inmemory", "react")

includeRoot(name = "bitframe-presenters", path = "bitframe-utils/presenters/core")

includeRoot(name = "validation", path = "bitframe-utils/validation")


//<Bitframe Testing>
includeSubs(base = "bitframe-testing", path = "bitframe-testing", "containers")

includeRoot(name = "bitframe-testing-instance-server", path = "bitframe-testing/instance/server")

includeSubs(base = "bitframe-testing-instance-client", path = "bitframe-testing/instance/client", "core", "browser")

includeSubs(base = "bitframe-testing-sdk", path = "bitframe-testing/sdk", "browser")
//</Bitframe Testing>

includeSubs(base = "bitframe-annotations", path = "bitframe-annotations", "core", "processor")

includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core", "test")

includeSubs(base = "bitframe-service", path = "bitframe-services", "core")

includeSubs(base = "bitframe-service-client", path = "bitframe-services/client", "core", "ktor")

includeSubs(base = "bitframe-authentication", path = "bitframe-authentication", "core")

includeSubs(base = "bitframe-authentication-service", path = "bitframe-authentication/services", "core")

includeSubs(base = "bitframe-authentication-service-client", path = "bitframe-authentication/services/client", "core", "ktor", "test")

includeSubs(base = "bitframe-authentication-service-server", path = "bitframe-authentication/services/server", "core")

includeSubs(base = "bitframe-authentication-dao", path = "bitframe-authentication/daos", "core", "inmemory")

includeSubs(base = "bitframe-server-dao", path = "bitframe-server/daos", "core", "inmemory")

includeSubs(base = "bitframe-server-framework", path = "bitframe-server/frameworks", "core", "test", "ktor")

includeSubs(base = "bitframe-sdk-client", path = "bitframe-sdk/client", "core", "ktor")

includeSubs(base = "bitframe-sdk-server", path = "bitframe-sdk/server", "core")

includeSubs(base = "bitframe-client", path = "bitframe-client", "viewmodels")

includeSubs(base = "bitframe-ui", path = "bitframe-client/ui", "react")

include(":pi-monitor")

includeRoot(name = "pi-monitor-core", path = "pi-monitor/pi-monitor-core")

includeSubs(base = "pi-monitor-dashboard", path = "pi-monitor/pi-monitor-integrations/dashboard", "core", "picortex")

includeRoot(name = "pi-monitor-server", path = "pi-monitor/pi-monitor-server")

includeSubs(base = "pi-monitor-dao", path = "pi-monitor/pi-monitor-daos", "core", "inmemory")

includeSubs(base = "pi-monitor-service", path = "pi-monitor/pi-monitor-services", "core", "test")

includeSubs(base = "pi-monitor-service-client", path = "pi-monitor/pi-monitor-services/client", "core", "ktor")

includeSubs(base = "pi-monitor-service-server", path = "pi-monitor/pi-monitor-services/server", "core")

includeSubs(base = "pi-monitor-client-sdk", path = "pi-monitor/pi-monitor-client/sdks", "core", "full")

includeSubs(base = "pi-monitor-client", path = "pi-monitor/pi-monitor-client", "test")

includeSubs(base = "pi-monitor-test", path = "pi-monitor/pi-monitor-test", "testing")

includeSubs(base = "pi-monitor-client-browser", path = "pi-monitor/pi-monitor-client/browser", "react")