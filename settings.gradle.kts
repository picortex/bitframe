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

// <Bitframe Utils>
includeSubs("mailer", "bitframe-utils/mailer", "api", "mock", "smtp")

includeRoot("templater", "bitframe-utils/templater")

includeSubs("bitframe-actor", "bitframe-actor", "core", "app", "user", "space")

includeSubs(base = "bitframe-events", path = "bitframe-utils/events", "core", "inmemory", "react")

includeSubs(base = "bitframe-events", path = "bitframe-utils/events", "core", "inmemory", "react")

includeRoot(name = "bitframe-presenters", path = "bitframe-utils/presenters/core")

includeRoot(name = "validation", path = "bitframe-utils/validation")

includeRoot(name = "response", path = "bitframe-utils/response")


//<Bitframe Testing>
//includeSubs(base = "bitframe-testing", path = "bitframe-testing", "containers")

//includeRoot(name = "bitframe-testing-instance-server", path = "bitframe-testing/instance/server")

//includeSubs(base = "bitframe-testing-instance-client", path = "bitframe-testing/instance/client", "core", "browser")

//includeSubs(base = "bitframe-testing-sdk", path = "bitframe-testing/sdk", "browser")
//</Bitframe Testing>

//includeSubs(base = "bitframe-annotations", path = "bitframe-annotations", "core", "processor")

includeSubs(base = "bitframe-dao", path = "bitframe-daos", "core", "mock", "mongo")

// services
includeSubs(base = "bitframe-service-config", path = "bitframe-service/config", "core", "daod")

includeSubs(base = "bitframe-service-config-api", path = "bitframe-service/config/api", "core", "ktor", "mock")

includeSubs(base = "bitframe-service-config-sdk-client", path = "bitframe-service/config/sdk/client", "core", "react")

includeSubs(base = "bitframe-service-config-sdk-server", path = "bitframe-service/config/sdk/server", "core")

includeSubs(base = "bitframe-service-generic", path = "bitframe-service/generic", "core", "daod")

includeSubs(base = "bitframe-service-generic-api", path = "bitframe-service/generic/api", "core", "ktor", "mock")

includeSubs(base = "bitframe-service-generic-sdk-server", path = "bitframe-service/generic/sdk/server", "core")

includeSubs(base = "bitframe-authentication", path = "bitframe-features/authentication", "core", "daod")

includeSubs(base = "bitframe-authentication-api", path = "bitframe-features/authentication/api", "core", "ktor", "mock")

includeSubs(base = "bitframe-authentication-sdk-client", path = "bitframe-features/authentication/sdk/client", "core", "react")

includeSubs(base = "bitframe-authentication-sdk-server", path = "bitframe-features/authentication/sdk/server", "core")

includeSubs(base = "bitframe-server-framework", path = "bitframe-server/frameworks", "core", "test", "ktor")

includeSubs(base = "bitframe-api", "bitframe-api", "core", "ktor", "mock")

includeSubs(base = "bitframe-sdk-client", path = "bitframe-sdk/client", "core", "react")

includeSubs(base = "bitframe-sdk-server", path = "bitframe-sdk/server", "core")

includeSubs(base = "bitframe-ui", path = "bitframe-client/ui", "react")

includeRoot(name = "pimonitor-core", "pimonitor/pimonitor-core")
includeRoot(name = "pimonitor-daod", "pimonitor/pimonitor-daod")
includeSubs(base = "pimonitor-api", "pimonitor/pimonitor-api", "core", "ktor", "mock", "public")

//include(":pi-monitor")
//
//includeRoot(name = "pi-monitor-core", path = "pi-monitor/pi-monitor-core")
//
//includeSubs(base = "pi-monitor-dashboard-integration", path = "pi-monitor/pi-monitor-features/dashboard-integrations", "core", "picortex")
//
//includeRoot(name = "pi-monitor-server", path = "pi-monitor/pi-monitor-server")
//
//includeSubs(base = "pi-monitor-service", path = "pi-monitor/pi-monitor-services", "core", "daod", "test")
//
//includeSubs(base = "pi-monitor-service-client", path = "pi-monitor/pi-monitor-services/client", "core", "ktor", "mock")
//
//includeSubs(base = "pi-monitor-service-server", path = "pi-monitor/pi-monitor-services/server", "core")
//
//includeSubs(base = "pi-monitor-api", path = "pi-monitor/pi-monitor-api", "core", "ktor", "mock")
//
//includeSubs(base = "pi-monitor-sdk-client", path = "pi-monitor/pi-monitor-sdk/client", "core", "react", "react-ktor")
//
//includeSubs(base = "pi-monitor-client", path = "pi-monitor/pi-monitor-client", "test")
//
//// includeSubs(base = "pi-monitor-test", path = "pi-monitor/pi-monitor-test", "testing")
//
//includeSubs(base = "pi-monitor-client-browser", path = "pi-monitor/pi-monitor-client/browser", "react")